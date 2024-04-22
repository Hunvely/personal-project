package com.rod.api.user.model;

import com.rod.api.article.model.Article;
import com.rod.api.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity // DB의 테이블과 매핑되는 객체로 DB와 상호작용을 담당
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
@ToString(exclude = {"id"})
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String token;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles;

}

