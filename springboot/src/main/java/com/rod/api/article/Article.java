package com.rod.api.article;

import com.rod.api.board.Board;
import com.rod.api.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"id"})
public class Article {
    @Id
    @Column(name="article_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String writer;
    @Column
    private String registerDate;

//    @ManyToOne
//    //@JoinColumn(name = "board_id")
//    private Board board;
//    @ManyToOne
//    //@JoinColumn(name = "user_id")
//    private User user;
    @Builder(builderMethodName = "builder")
    public Article(String title, String content, String writer, String registerDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.registerDate = registerDate;

    }
}
