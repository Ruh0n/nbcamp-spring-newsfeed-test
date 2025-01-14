package com.ptjcoding.nbcampspringnewsfeed.global.jwt.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "refresh_token")
@Entity
public class RefreshTokenEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "refresh_token_id")
  private Long id;
  private Long memberId;
  private String token;

  protected RefreshTokenEntity() {
  }

  private RefreshTokenEntity(Long memberId, String token) {
    this.memberId = memberId;
    this.token = token;
  }

  public static RefreshTokenEntity of(Long memberId, String token) {
    return new RefreshTokenEntity(memberId, token);
  }

}
