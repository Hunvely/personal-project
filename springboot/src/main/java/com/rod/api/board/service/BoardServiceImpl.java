package com.rod.api.board.service;

import com.rod.api.board.model.Board;
import com.rod.api.board.model.BoardDto;
import com.rod.api.board.repository.BoardRepository;
import com.rod.api.common.component.Messenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Messenger modify(BoardDto boardDto) {
        Optional<Board> optionalBoard = boardRepository.findById(boardDto.getId());
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            Board updateBoard = board.toBuilder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .description(boardDto.getDescription())
                    .build();

            boardRepository.save(updateBoard);

            return Messenger.builder()
                    .message("Update SUCCESS")
                    .build();
        } else {
            log.warn("Board with ID '{}' not found.", boardDto.getId());

            return Messenger.builder()
                    .message("Update FAILURE")
                    .build();
        }
    }

    @Override
    public List<BoardDto> findAll() throws SQLException {
        return boardRepository.findAllByOrderByContentDesc().stream().map(i -> entityToDto(i)).toList();
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
