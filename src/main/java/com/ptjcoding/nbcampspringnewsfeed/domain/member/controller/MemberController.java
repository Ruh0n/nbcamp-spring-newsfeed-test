package com.ptjcoding.nbcampspringnewsfeed.domain.member.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.NicknameUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberInfoDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.NicknameChangeDto;
import com.ptjcoding.nbcampspringnewsfeed.global.enums.GlobalSuccessCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/signup")
  public ResponseEntity<CommonResponseDto<MemberResponseDto>> signup(
      @Validated @RequestBody SignupRequestDto dto
  ) {
    return CommonResponseDto.ok(GlobalSuccessCode.CREATE, memberService.signup(dto));
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponseDto<Void>> login(
      @Validated @RequestBody LoginRequestDto dto,
      HttpServletResponse response
  ) {
    memberService.login(dto, response);

    return CommonResponseDto.ok(GlobalSuccessCode.LOGIN, null);
  }

  @PostMapping("/logout")
  public ResponseEntity<CommonResponseDto<Void>> logout(
      @RequestAttribute("member") Member member,
      HttpServletRequest request
  ) {
    memberService.logout(request, member);

    return CommonResponseDto.ok(GlobalSuccessCode.LOGOUT, null);
  }

  @DeleteMapping("/{memberId}")
  public ResponseEntity<CommonResponseDto<Void>> delete(
      @PathVariable("memberId") Long memberId
  ) {
    memberService.delete(memberId);

    return CommonResponseDto.ok(GlobalSuccessCode.DELETE, null);
  }

  @GetMapping("/{memberId}")
  public ResponseEntity<CommonResponseDto<MemberInfoDto>> memberInfo(
      @PathVariable("memberId") Long memberId
  ) {
    return CommonResponseDto.ok(GlobalSuccessCode.SEARCH, memberService.memberInfo(memberId));
  }

  @PostMapping
  public ResponseEntity<CommonResponseDto<NicknameChangeDto>> updateMemberName(
      @RequestAttribute("member") Member member,
      @Validated @RequestBody NicknameUpdateRequestDto dto
  ) {
    NicknameChangeDto responseDto = memberService.updateMemberName(member, dto);

    return CommonResponseDto.ok(GlobalSuccessCode.UPDATE, responseDto);
  }

}
