package com.rod.api.board;

import com.rod.api.article.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TABLE_BOARD")
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

