package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto implements Serializable {

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;

  @NotBlank
  @Pattern(regexp = "^(?!.+ ).+$")
  @Pattern(regexp = "^(?=.*\\d).+$")
  @Pattern(regexp = "^(?=.*[a-z]).+$")
  @Pattern(regexp = "^(?=.*[A-Z]).+$")
  private String password;

}
