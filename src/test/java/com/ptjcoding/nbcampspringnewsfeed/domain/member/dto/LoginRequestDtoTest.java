package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.Test_Values;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("LoginRequestDto 테스트")
class LoginRequestDtoTest extends Test_Values {

  private static Validator validator;

  @BeforeAll
  static void beforeAll() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("성공 테스트")
  class LoginRequestDtoTest_Success {

    @Test
    @DisplayName("성공")
    void success() {
      // given
      LoginRequestDto loginRequestDto = new LoginRequestDto(VALID_EMAIL, VALID_PASSWORD);

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      // then
      violations.forEach(x -> System.err.println(x.getMessage()));
      assertTrue(violations.isEmpty());
    }

  }

  @Nested
  @DisplayName("실패 테스트")
  class LoginRequestDtoTest_Failure {

    @ParameterizedTest
    @ValueSource(strings = {" ", "email.com"})
    @DisplayName("이메일 실패")
    void emailFailure_Blank(String email) {
      // given
      LoginRequestDto loginRequestDto = new LoginRequestDto(email, VALID_PASSWORD);

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      // then
      violations.forEach(x -> System.err.println(x.getMessage()));
      assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "Password 123", "password123", "PASSWORD123", "PasswordOne"})
    @DisplayName("비밀번호 실패")
    void passwordFailure_Blank(String password) {
      // given
      LoginRequestDto loginRequestDto = new LoginRequestDto(VALID_EMAIL, password);

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      // then
      violations.forEach(violation -> System.err.println(violation.getMessage()));
      assertFalse(violations.isEmpty());
    }

  }

}
