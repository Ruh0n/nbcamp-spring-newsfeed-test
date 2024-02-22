package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.NicknameUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NicknameUpdateDto {

  private String nickname;

  public static NicknameUpdateDto of(NicknameUpdateRequestDto dto) {
    return new NicknameUpdateDto(dto.getNickname());
  }

}
