package com.rod.api.board.service;

import com.rod.api.board.model.Board;
import com.rod.api.board.model.BoardDto;
import com.rod.api.common.service.CommandService;
import com.rod.api.common.service.QueryService;

public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {

    default Board dtoToEntity(BoardDto boardDto) {
        return Board.builder()
                .id(boardDto.getId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .description(boardDto.getDescription())
                .build();
    }

    default BoardDto entityToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .description(board.getDescription())
                .build();
    }
}
