package com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt;

import lombok.Getter;

@Getter
public enum JwtErrorCode {
  JWT_SIGNATURE_EXCEPTION("Invalid JWT signature"),
  JWT_EXPIRED_EXCEPTION("JWT token is expired"),
  UN_SUPPORTED_TOKEN_TYPE_EXCEPTION("Unsupported JWT token type"),
  INVALID_TOKEN_EXCEPTION("Invalid JWT token");

  private final String message;

  JwtErrorCode(String message) {
    this.message = message;
  }
}
