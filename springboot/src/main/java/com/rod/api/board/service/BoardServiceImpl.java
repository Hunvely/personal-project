package com.rod.api.board.service;

import com.rod.api.board.model.BoardDto;
import com.rod.api.board.repository.BoardRepository;
import com.rod.api.common.component.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private BoardRepository boardRepository;

    @Override
    public Messenger save(BoardDto boardDto) {
        return null;
    }

    @Override
    public Messenger deleteById(Long id) {
        return null;
    }

    @Override
    public Messenger modify(BoardDto boardDto) {
        return null;
    }

    @Override
    public List<BoardDto> findAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<BoardDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }
}
