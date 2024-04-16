package com.rod.api.common.component;


import com.rod.api.user.model.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Slf4j
@Component
public class JwtProvider {
    @Value("${jwt.iss}")
    private String issuer;

    public final SecretKey secretKey;

    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);
    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createToken(UserDto userDto) {

        String token = Jwts.builder()
                .issuer(issuer)
                .signWith(secretKey)
                .expiration(Date.from(expiredDate))
                .subject("rod")
                .claim("username", userDto.getUsername())
                .claim("userId", userDto.getId())
                .compact();

        log.info("로그인 성공으로 발급된 토큰 : " + token);

        return token;
    }


}
