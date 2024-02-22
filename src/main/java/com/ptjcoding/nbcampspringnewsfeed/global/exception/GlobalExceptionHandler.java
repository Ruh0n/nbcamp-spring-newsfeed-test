package com.ptjcoding.nbcampspringnewsfeed.global.exception;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.CustomJwtException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({CustomJwtException.class, CustomRuntimeException.class})
  public ResponseEntity<CommonResponseDto<String>> customExceptionHandler(Exception e) {
    return CommonResponseDto.badRequest(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CommonResponseDto<List<String>>> methodArgumentNotValidException(
      MethodArgumentNotValidException e
  ) {
    return CommonResponseDto.of(
        HttpStatus.BAD_REQUEST,
        GlobalErrorCode.ILLEGAL_INPUT.getMessage(),
        null
    );
  }

}
