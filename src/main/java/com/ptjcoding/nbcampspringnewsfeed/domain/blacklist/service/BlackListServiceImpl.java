package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto.BlackListRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlackListServiceImpl implements BlackListService {

  private final BlackListRepository blackListRepository;

  @Override
  public void register(BlackListRequestDto dto) {
    blackListRepository.register(dto.getEmail());
  }

  @Override
  public void deregister(BlackListRequestDto dto) {
    blackListRepository.deregister(dto.getEmail());
  }

}
