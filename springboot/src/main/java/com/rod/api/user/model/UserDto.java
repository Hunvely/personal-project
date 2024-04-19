package com.rod.api.user.model;

import com.rod.api.article.model.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto { // DTO는 데이터 전송을 위한 객체로 클라이언트와 서버의 통신 데이터를 담당

    private Long id;
    @Size(min = 5, max = 15)
    @Pattern(regexp = "[a-z0-9]*")
    @NotBlank
    private String username;
    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    private String token;

    private List<Article> articles;

}
