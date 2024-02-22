package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto.BlackListRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.service.BlackListService;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.global.enums.GlobalSuccessCode;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/blacklists")
@RequiredArgsConstructor
public class BlackListController {

  private final BlackListService blackListService;

  @PostMapping("/register")
  public ResponseEntity<CommonResponseDto<String>> register(
      @RequestAttribute("member") Member member,
      @Validated @RequestBody BlackListRequestDto dto
  ) {
    if (member.isRole(MemberRole.USER)) {
      return CommonResponseDto.badRequest(GlobalErrorCode.UNAUTHORIZED.getMessage());
    }

    blackListService.register(dto);
    return CommonResponseDto.ok(GlobalSuccessCode.REGISTER, dto.getEmail());
  }

  @DeleteMapping("/deregister")
  public ResponseEntity<CommonResponseDto<String>> deregister(
      @RequestAttribute("member") Member member,
      @Validated @RequestBody BlackListRequestDto dto
  ) {
    if (member.isRole(MemberRole.USER)) {
      return CommonResponseDto.badRequest(GlobalErrorCode.UNAUTHORIZED.getMessage());
    }

    blackListService.deregister(dto);
    return CommonResponseDto.ok(GlobalSuccessCode.DELETE, dto.getEmail());
  }

}
