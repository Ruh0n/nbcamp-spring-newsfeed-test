package com.ptjcoding.nbcampspringnewsfeed.global.jwt.repository;

import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.CustomJwtException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.jwt.JwtErrorCode;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.infrastructure.jpa.RefreshTokenJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.infrastructure.jpa.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

  private final RefreshTokenJpaRepository refreshTokenJpaRepository;

  @Override
  public void register(Long memberId, String token) {
    RefreshTokenEntity entity = RefreshTokenEntity.of(memberId, token);
    refreshTokenJpaRepository.save(entity);
  }

  @Override
  public Long findMemberIdByToken(String token) {
    RefreshTokenEntity entity = refreshTokenJpaRepository.findByToken(token)
        .orElseThrow(() ->
            new CustomJwtException(JwtErrorCode.INVALID_TOKEN_EXCEPTION));
    return entity.getMemberId();
  }

  @Override
  public void deleteByMemberId(Long memberId) {
    refreshTokenJpaRepository.deleteByMemberId(memberId);
  }

  @Override
  public void deleteToken(String token) {
    refreshTokenJpaRepository.deleteByToken(token);
  }

}
