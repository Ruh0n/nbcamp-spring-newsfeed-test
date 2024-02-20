package com.ptjcoding.nbcampspringnewsfeed.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ptjcoding.nbcampspringnewsfeed.global.enums.GlobalSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseDto<T> {

  private HttpStatus status;
  private String message;
  private T data;

  public static <T> ResponseEntity<CommonResponseDto<T>> ok(
      GlobalSuccessCode globalSuccessCode,
      T data
  ) {
    return ResponseEntity.ok(
        new CommonResponseDto<>(HttpStatus.OK, globalSuccessCode.getMessage(), data));
  }

  public static <T> ResponseEntity<CommonResponseDto<T>> badRequest(String message) {
    return ResponseEntity
        .badRequest()
        .body((new CommonResponseDto<>(HttpStatus.BAD_REQUEST, message, null)));
  }

  public static <T> ResponseEntity<CommonResponseDto<T>> of(
      HttpStatus status, String message, T data
  ) {
    return ResponseEntity.status(status).body(new CommonResponseDto<>(status, message, data));
  }

  public String getStatus() {
    return status.toString();
  }

}
