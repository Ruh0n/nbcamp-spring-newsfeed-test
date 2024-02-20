package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import static com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository.BlackListRepositoryImpl;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository.BookmarkRepositoryImpl;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.CommentRepositoryImpl;
import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.repository.HallOfFameRepositoryImpl;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.NicknameUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepositoryImpl;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.NicknameUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.NicknameChangeDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepositoryImpl;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.VoteRepositoryImpl;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.repository.TokenRepositoryImpl;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

  @Mock
  private MemberRepositoryImpl memberRepository;
  @Mock
  private PostRepositoryImpl postRepository;
  @Mock
  private CommentRepositoryImpl commentRepository;
  @Mock
  private VoteRepositoryImpl voteRepository;
  @Mock
  private BookmarkRepositoryImpl bookmarkRepository;
  @Mock
  private HallOfFameRepositoryImpl hallOfFameRepository;
  @Mock
  private BlackListRepositoryImpl blackListRepository;
  @Mock
  private TokenRepositoryImpl tokenRepository;

  @Mock
  private JwtProvider jwtProvider;

  @InjectMocks
  private MemberServiceImpl memberService;
  private static FixtureMonkey sut;
  private static final String validEmail = "email@example.com";
  private static final String validPassword = "Password123";


  @BeforeAll
  static void beforeAll() {
    sut = FixtureMonkey.builder()
        .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
        .build();
  }

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(jwtProvider, "secretKey", "IAMASUPERSECRETKEYIAMASUPERSECRETKEYIAMASUPERSECRETKEY");
    jwtProvider.init();
    ReflectionTestUtils.setField(memberService, "jwtProvider", jwtProvider);
  }

  @Nested
  @DisplayName("성공 테스트")
  class Success {

    @Test
    void signup() {
      // given
      SignupRequestDto requestDto = sut.giveMeBuilder(SignupRequestDto.class)
          .set("email", validEmail)
          .set("password", validPassword)
          .set("checkPassword", validPassword)
          .sample();
      // when
      MemberResponseDto responseDto = memberService.signup(requestDto);

      // then
      assertEquals(requestDto.getEmail(), responseDto.getEmail());
      assertEquals(requestDto.getNickname(), responseDto.getNickname());
    }

    @Test
    void login() {
      // Mockito BDDMockito

      // given
      MockHttpServletResponse response = new MockHttpServletResponse();
      LoginRequestDto requestDto = sut.giveMeBuilder(LoginRequestDto.class)
          .set("email", validEmail)
          .set("password", validPassword)
          .sample();

      given(blackListRepository.checkEmail(requestDto.getEmail())).willReturn(false);
      given(memberRepository.checkEmail(requestDto.getEmail())).willReturn(true);

      String accessToken = "I am a valid access token";
      String refreshToken = "I am a valid refresh token";
      given(jwtProvider.generateAccessToken(1L, USER.getAuthority())).willReturn(accessToken);
      given(jwtProvider.generateRefreshToken(1L, USER.getAuthority())).willReturn(refreshToken);

      // when
      memberService.login(requestDto, response);

      // then
      then(jwtProvider).should(times(1)).addAccessTokenToCookie(accessToken, response);
      then(jwtProvider).should(times(1)).addRefreshTokenToCookie(refreshToken, response);
    }

    @Test
    void updateMemberName() {
      // given
      Member member = sut.giveMeOne(Member.class);
      NicknameUpdateRequestDto requestDto = sut.giveMeOne(NicknameUpdateRequestDto.class);

      Member changeMember = Member.builder().nickname(requestDto.getNickname()).build();
      given(memberRepository.updateMember(eq(member.getId()), any(NicknameUpdateDto.class))).willReturn(changeMember);

      // when
      NicknameChangeDto changeDto = memberService.updateMemberName(member, requestDto);

      // then
      assertEquals(requestDto.getNickname(), changeDto.getAfterName());
    }

  }

  @Nested
  @DisplayName("실패 테스트")
  class Failure {

    @Nested
    class Signup {

      @Test
      @Description("Signup: 이메일 중복")
      void signup() {
        // given
        SignupRequestDto requestDto = sut.giveMeBuilder(SignupRequestDto.class)
            .set("email", validEmail)
            .set("password", validPassword)
            .set("checkPassword", validPassword)
            .sample();

        given(memberRepository.checkEmail(validEmail)).willReturn(true);

        // when - then
        assertThrows(CustomRuntimeException.class, () -> memberService.signup(requestDto));
      }

    }

    @Nested
    class Login {

      @Test
      @Description("블랙리스트에 등록된 이메일")
      void login() {
        // given
        MockHttpServletResponse response = new MockHttpServletResponse();
        LoginRequestDto requestDto = sut.giveMeBuilder(LoginRequestDto.class)
            .set("email", validEmail)
            .set("password", validPassword)
            .sample();

        given(blackListRepository.checkEmail(validEmail)).willReturn(true);
        given(memberRepository.checkEmail(validEmail)).willReturn(true);
        // when - then
        assertThrows(CustomRuntimeException.class, () -> memberService.login(requestDto, response));
      }

    }

  }

}
