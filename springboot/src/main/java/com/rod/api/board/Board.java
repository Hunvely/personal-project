package com.rod.api.board;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"id"})
public class Board {

    @Id
    @Column(name = "board_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String boardName;
    @Column
    private String boardType;

    @Builder(builderMethodName = "builder")
    public Board(String boardName, String boardType) {
        this.boardName = boardName;
        this.boardType = boardType;
    }
}

