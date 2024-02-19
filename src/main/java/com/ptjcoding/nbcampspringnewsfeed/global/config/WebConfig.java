package com.ptjcoding.nbcampspringnewsfeed.global.config;

import com.ptjcoding.nbcampspringnewsfeed.global.interceptor.AuthenticationInterceptor;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtProvider jwtProvider;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(
                        jwtProvider
                ))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/css/*",
                        "/*.ico",
                        "/error",
                        "/",
                        "/api/**/members/login",
                        "/api/**/members/signup",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                );
    }
}
