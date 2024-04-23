package com.rod.api.common.config;

import com.rod.api.common.component.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 인터셉터 등록
        registry.addInterceptor(authInterceptor) // Servlet 컨테이너의 객체를 Spring 컨테이너와 연결
                .excludePathPatterns("/api/auth/**") // 로그인 할 때는 토큰이 없기 때문에 인터셉터를 제거해야 함.
                .addPathPatterns("/api/**"); // /api/로 시작하는 경로에 대한 모든 요청을 인터셉트
    }
}
