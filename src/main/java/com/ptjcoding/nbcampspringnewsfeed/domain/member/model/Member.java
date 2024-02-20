package com.ptjcoding.nbcampspringnewsfeed.domain.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  private Long id;
  private String email;
  private String nickname;
  private String password;
  private MemberRole role;

  public boolean isRole(MemberRole memberRole) {
    return role.getAuthority().equals(memberRole.getAuthority());
  }

}
