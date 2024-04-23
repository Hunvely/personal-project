package com.rod.api.common.component.security;


import com.rod.api.user.model.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
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
                .claim("id", userDto.getId())
                .compact();

        log.info("로그인 성공으로 발급된 토큰 : " + token);

        return token;
    }


    public String extractTokenFromHeader(HttpServletRequest request) {
        log.info("프론트에서 넘어온 Request 값 : {}", request.getServletPath());
        String bearerToken = request.getHeader("Authorization");
        log.info("프론트에서 넘어온 토큰 값 : {}", bearerToken);

        return bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : "undefined";
    }

    public static void printPayload(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        log.info("JWT Provider Access Token header : " + header);
        log.info("JWT Provider Access Token payload : " + payload);

        // return new StringBuilder().append(header).append(payload).toString();
    }

    public Claims getPayload(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload(); // 정형화
    }
}
