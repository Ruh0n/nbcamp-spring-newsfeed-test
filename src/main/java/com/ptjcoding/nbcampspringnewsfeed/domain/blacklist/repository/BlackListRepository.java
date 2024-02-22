package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository;

public interface BlackListRepository {

  void register(String email);

  void deregister(String email);

  boolean checkEmail(String email);

}
