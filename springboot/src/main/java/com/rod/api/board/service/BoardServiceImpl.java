package com.rod.api.board.service;

import com.rod.api.board.model.Board;
import com.rod.api.board.model.BoardDto;
import com.rod.api.board.repository.BoardRepository;
import com.rod.api.common.component.Messenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Messenger save(BoardDto boardDto) {
        entityToDto(boardRepository.save(dtoToEntity(boardDto)));
        return Messenger.builder()
                .message("SUCCESS")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        boardRepository.deleteById(id);
        String msg = boardRepository.findById(id).isPresent() ? "SUCCESS" : "FAILURE";

        return Messenger.builder()
                .message(msg)
                .build();
    }

    @Override
    public Messenger modify(BoardDto boardDto) {
        return null;
    }

    @Override
    public List<BoardDto> findAll() throws SQLException {
        return boardRepository.findAll().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<BoardDto> findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            return Optional.of(entityToDto(board));
        } else {
            log.warn("Board with id '{}' not found.", id);

            return Optional.empty();
        }
    }

    @Override
    public long count() {
        return boardRepository.count();
    }

    @Override
    public Boolean existById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);

        return optionalBoard.isPresent();
    }
}
