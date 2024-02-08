package com.ptjcoding.nbcampspringnewsfeed.global.config;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.interceptor.AuthenticationInterceptor;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO: yet disable interceptor
//@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(
                        memberRepository,
                        jwtProvider
                ))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/css/*",
                        "/*.ico",
                        "/error",
                        "/",
                        "/api/**/users/login",
                        "/api/**/users/signup",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                );
    }
}