package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.NicknameUpdateDto;

public interface MemberRepository {

  void register(MemberSignupDto dto);

  boolean checkEmail(String email);

  void deleteMember(Long id);

  Member updateMember(Long id, NicknameUpdateDto dto);

  Member checkPassword(LoginRequestDto dto);

  Member findMemberOrElseThrow(Long id);

}
