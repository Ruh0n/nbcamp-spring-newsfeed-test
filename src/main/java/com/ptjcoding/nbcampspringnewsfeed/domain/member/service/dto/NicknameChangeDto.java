package com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NicknameChangeDto {

  private String prevName;
  private String afterName;

  public static NicknameChangeDto of(String prevName, String afterName) {
    return NicknameChangeDto.builder()
        .prevName(prevName)
        .afterName(afterName)
        .build();
  }

}
