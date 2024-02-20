package com.ptjcoding.nbcampspringnewsfeed.global.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

  private final GlobalErrorCode code;

  public CustomRuntimeException(final GlobalErrorCode code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return String.format("[RUNTIME ERROR] %s", code.getMessage());
  }

}
