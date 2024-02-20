package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.NicknameUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberInfoDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.NicknameChangeDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {

  MemberResponseDto signup(SignupRequestDto signupRequestDto);

  void login(LoginRequestDto loginRequestDto, HttpServletResponse response);

  void logout(HttpServletRequest request, Member member);

  void delete(Long memberId);

  MemberInfoDto memberInfo(Long memberId);

  NicknameChangeDto updateMemberName(
      Member member,
      NicknameUpdateRequestDto dto
  );

}
