package com.rod.api.common.component.interceptor;

import com.rod.api.common.component.security.JwtProvider;
import com.rod.api.user.model.User;
import com.rod.api.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Log4j2
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository; // 토큰 Repository에 저장했기 때문

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return Stream.of(request)
                .map(token -> jwtProvider.extractTokenFromHeader(token))
                .filter(token -> !token.equals("undefined"))
                .peek(token -> log.info("1 - 인터셉터 토큰 로그 Bearer 포함 : {}", token))
                .map(token -> jwtProvider.getPayload(token).get("id", Long.class))
                .peek(id -> log.info("2 - 인터셉터 사용자 id : {}", id))
                .map(id -> userRepository.findById(id))
                .filter(user -> user.isPresent())
                .findFirst()
                .isPresent()
                ;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
