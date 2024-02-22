package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("NicknameUpdateRequestDto 테스트")
class NicknameUpdateRequestDtoTest {

  private static final String validNickname = "nickname123";
  private static Validator validator;

  @BeforeAll
  static void beforeAll() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("성공 테스트")
  class NicknameUpdateRequestDtoTest_Success {

    @Test
    @DisplayName("성공")
    void success() {
      // given
      NicknameUpdateRequestDto nicknameUpdateRequestDto = new NicknameUpdateRequestDto();
      ReflectionTestUtils.setField(nicknameUpdateRequestDto, "nickname", validNickname);

      // when
      Set<ConstraintViolation<NicknameUpdateRequestDto>> violations = validator.validate(nicknameUpdateRequestDto);

      // then
      violations.forEach(x -> System.err.println(x.getMessage()));
      assertTrue(violations.isEmpty());
    }

  }

  @Nested
  @DisplayName("실패 테스트")
  class NicknameUpdateRequestDtoTest_Failure {

    @ParameterizedTest
    @ValueSource(strings = {" ", "n", "나는닉네임입니다"})
    @DisplayName("닉네임 실패")
    void emailFailure_Blank(String nickname) {
      // given
      NicknameUpdateRequestDto loginRequestDto = new NicknameUpdateRequestDto();
      ReflectionTestUtils.setField(loginRequestDto, "nickname", nickname);

      // when
      Set<ConstraintViolation<NicknameUpdateRequestDto>> violations = validator.validate(loginRequestDto);

      // then
      violations.forEach(x -> System.err.println(x.getMessage()));
      assertFalse(violations.isEmpty());
    }

  }

}