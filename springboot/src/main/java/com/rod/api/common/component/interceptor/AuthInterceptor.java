package com.rod.api.common.component.interceptor;

import com.rod.api.common.component.security.JwtProvider;
import com.rod.api.user.model.User;
import com.rod.api.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository; // 토큰 Repository에 저장했기 때문

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = jwtProvider.extractTokenFromHeader(request); // Body는 계속 바뀌고 Header 안에 넣으면 바뀌지 않기 때문에 토큰을 Header에 넣음.

        if (ObjectUtils.isEmpty(token)) { // 정형화
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

            return false;
        }

        String strId = jwtProvider.getPayload(token);
        Long id = Long.parseLong(strId);

        Optional<User> user = userRepository.findById(id);
        if (ObjectUtils.isEmpty(user)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

            return false;
        }

        return true;
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
