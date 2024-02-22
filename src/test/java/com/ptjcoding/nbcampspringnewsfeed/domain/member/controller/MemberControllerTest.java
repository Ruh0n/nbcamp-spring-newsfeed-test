package com.ptjcoding.nbcampspringnewsfeed.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.Test_Values;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import java.nio.charset.StandardCharsets;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = {MemberController.class})
class MemberControllerTest extends Test_Values {

  private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
      .plugin(new JakartaValidationPlugin())
      .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
      .build();

  @Autowired
  WebApplicationContext context;
  @Autowired
  ObjectMapper objectMapper;
  @MockBean
  JwtProvider jwtProvider;
  @MockBean
  MemberService memberService;
  private MockMvc mvc;

  @BeforeEach
  public void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(context)
        .defaultRequest(get("/").contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8))
        .alwaysExpect(content().contentType(MediaType.APPLICATION_JSON))
        .build();
  }

  @Nested
  class Signup {

    private static SignupRequestDto requestDto;

    @BeforeAll
    static void beforeAll() {
      requestDto = new SignupRequestDto(VALID_EMAIL, VALID_NICKNAME, VALID_PASSWORD, VALID_PASSWORD);
    }

    @Test
    @Description("성공")
    void success() throws Exception {
      // given
      // when - then
      mvc.perform(post("/api/v1/members/signup").content(objectMapper.writeValueAsString(requestDto)))
          .andExpect(status().isOk());
    }

  }

  @Nested
  class Login {

    private static LoginRequestDto requestDto;

    @BeforeAll
    static void setUp() {
      requestDto = new LoginRequestDto(VALID_EMAIL, VALID_PASSWORD);
    }

    @Test
    @Description("성공")
    void login() throws Exception {
      // given

      // when - then
      mvc.perform(post("/api/v1/members/login").content(objectMapper.writeValueAsString(requestDto)))
          .andExpect(status().isOk());
    }

  }

  @Nested
  class Logout {

    @Test
    @Description("성공")
    void logout() throws Exception {
      mvc.perform(post("/api/v1/members/logout"))
          .andExpect(status().isOk());
    }

  }

  @Nested
  class Delete {

  }

  @Nested
  class MemberInfo {

  }

  @Nested
  class UpdateMemberName {

  }

}
