package com.ptjcoding.nbcampspringnewsfeed.global.interceptor;

import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.CustomJwtException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.JwtErrorCode;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.TokenState;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

  private final JwtProvider jwtProvider;

  @Override
  public boolean preHandle(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler
  ) throws Exception {
    if (isSwaggerRequest(request)) {
      return true;
    }

    String tokenValue = jwtProvider.getAccessTokenFromRequest(request);
    String token = jwtProvider.substringToken(tokenValue);
    TokenState state = jwtProvider.checkTokenState(token);

    if (state.equals(TokenState.INVALID)) {
      throw new CustomJwtException(JwtErrorCode.INVALID_TOKEN_EXCEPTION);
    }

    if (state.equals(TokenState.EXPIRED)) {
      String accessToken = jwtProvider.reGenerateAccessToken(request);
      String subtractToken = jwtProvider.substringToken(accessToken);
      jwtProvider.addAccessTokenToCookie(accessToken, response);
      jwtProvider.setMemberInfoToRequest(subtractToken, request);
    } else {
      jwtProvider.setMemberInfoToRequest(token, request);
    }

    return true;
  }

  private boolean isSwaggerRequest(final HttpServletRequest request) {
    String uri = request.getRequestURI();
    return uri.contains("swagger")
           || uri.contains("api-docs")
           || uri.contains("webjars");
  }

}
