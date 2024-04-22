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
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor) // Servlet 컨테이너의 객체를 Spring 컨테이너와 연결
                .excludePathPatterns("/favion.ico")
                .addPathPatterns("/api/**");
    }
}
