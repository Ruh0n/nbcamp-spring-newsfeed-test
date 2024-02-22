package com.ptjcoding.nbcampspringnewsfeed.global.config;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.Test_Values;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig implements WebMvcConfigurer {
  // do not implement interceptor


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new FakeAuthInterceptor()).order(1)
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

  public static class FakeAuthInterceptor extends Test_Values implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request, HttpServletResponse response, Object handler
    ) {
      Member fakeMember = new Member(VALID_ID, VALID_EMAIL, VALID_NICKNAME, VALID_PASSWORD, MemberRole.USER);
      request.setAttribute("member", fakeMember);

      return true;
    }

  }

}
