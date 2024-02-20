package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure.BlackListJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure.entity.BlackListEntity;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BlackListRepositoryImpl implements BlackListRepository {

  private final BlackListJpaRepository blackListJpaRepository;

  @Override
  public void register(String email) {
    if (checkEmail(email)) {
      throw new CustomRuntimeException(GlobalErrorCode.ALREADY_EXIST);
    }

    blackListJpaRepository.save(BlackListEntity.of(email));
  }

  @Override
  public void deregister(String email) {
    BlackListEntity findEntity = blackListJpaRepository.findByEmail(email)
        .orElseThrow(() -> new CustomRuntimeException(GlobalErrorCode.NOT_FOUND));

    blackListJpaRepository.delete(findEntity);
  }

  @Override
  public boolean checkEmail(String email) {
    return blackListJpaRepository.findByEmail(email).isPresent();
  }

}
