package com.ptjcoding.nbcampspringnewsfeed.domain.member.model;

public enum MemberRole {
  ADMIN(Authority.ADMIN),
  USER(Authority.USER);

  private final String authority;

  MemberRole(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return authority;
  }

  private static class Authority {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

  }
}
