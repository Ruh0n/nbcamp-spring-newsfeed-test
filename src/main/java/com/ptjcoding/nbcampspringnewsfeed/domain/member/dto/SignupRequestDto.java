package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;

  @NotBlank
  @Pattern(regexp = "^(?![^\\s]+[\\s\\W]+$)(?!\\W*[\\s\\W]*$).+$")
  @Size(min = 2, max = 15)
  private String nickname;

  @NotBlank
  @Pattern(regexp = "^(?!.+ ).+$")
  @Pattern(regexp = "^(?=.*\\d).+$")
  @Pattern(regexp = "^(?=.*[a-z]).+$")
  @Pattern(regexp = "^(?=.*[A-Z]).+$")
  private String password;

  @NotBlank
  @Pattern(regexp = "^(?!.+ ).+$")
  @Pattern(regexp = "^(?=.*\\d).+$")
  @Pattern(regexp = "^(?=.*[a-z]).+$")
  @Pattern(regexp = "^(?=.*[A-Z]).+$")
  private String checkPassword;

}
