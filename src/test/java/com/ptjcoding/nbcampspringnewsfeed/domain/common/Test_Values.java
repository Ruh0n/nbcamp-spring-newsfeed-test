package com.ptjcoding.nbcampspringnewsfeed.domain.common;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;

public abstract class Test_Values {

  public static final Long VALID_ID = 1L;

  public static final String VALID_NICKNAME = "nickname1";
  public static final String VALID_EMAIL = "email@email.com";
  public static final String VALID_PASSWORD = "Password123";

  public static final Member VALID_MEMBER = new Member(VALID_ID, VALID_EMAIL, VALID_NICKNAME, VALID_PASSWORD, MemberRole.USER);

}
