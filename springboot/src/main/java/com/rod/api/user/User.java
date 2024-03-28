package com.rod.api.user;

import com.rod.api.article.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="TABLE_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"id"})
public class User {
    @Id
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String eMail;

    @OneToMany
    @JoinColumn(name = "users_user_id", referencedColumnName = "user_id")
    private List<Article> articles;



    @Builder(builderMethodName = "builder")
    public User(String username, String password, String name, String phoneNumber, String eMail) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }

}

