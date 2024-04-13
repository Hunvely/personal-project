package com.rod.api.board.service;

import com.rod.api.board.model.Board;
import com.rod.api.board.model.BoardDto;
import com.rod.api.common.service.CommandService;
import com.rod.api.common.service.QueryService;

public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {

    default Board dtoToEntity(BoardDto boardDto) {
        return Board.builder()
                .id(boardDto.getId())
                .boardName(boardDto.getBoardName())
                .boardType(boardDto.getBoardType())
                .build();
    }

    default BoardDto entityToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .boardName(board.getBoardName())
                .boardType(board.getBoardType())
                .build();
    }
}
