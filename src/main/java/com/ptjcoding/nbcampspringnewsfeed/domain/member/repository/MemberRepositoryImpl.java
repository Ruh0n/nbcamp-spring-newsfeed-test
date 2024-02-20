package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.MemberJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.entity.MemberEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.NicknameUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

  private final MemberJpaRepository memberJpaRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void register(MemberSignupDto dto) {
    memberJpaRepository.save(MemberEntity.of(
        dto.getEmail(),
        dto.getNickname(),
        passwordEncoder.encode(dto.getPassword()),
        MemberRole.USER
    ));
  }

  @Override
  public Member checkPassword(LoginRequestDto dto) {
    MemberEntity entity = memberJpaRepository.findByEmail(dto.getEmail()).orElseThrow(
        EntityNotFoundException::new
    );

    if (!passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
      throw new CustomRuntimeException(GlobalErrorCode.NOT_EQUAL);
    }

    return entity.toModel();
  }

  @Override
  public Member findMemberOrElseThrow(Long id) {
    return findMember(id).toModel();
  }

  @Override
  public boolean checkEmail(String email) {
    return memberJpaRepository.findByEmail(email).isPresent();
  }

  @Override
  public void deleteMember(Long id) {
    MemberEntity memberEntity = findMember(id);

    memberJpaRepository.delete(memberEntity);
  }

  @Override
  public Member updateMember(Long id, NicknameUpdateDto dto) {
    MemberEntity memberEntity = findMember(id);

    if (memberEntity.isCurrentName(dto.getNickname())) {
      throw new CustomRuntimeException(GlobalErrorCode.UNCHANGED);
    }

    memberEntity.update(dto);
    return memberEntity.toModel();
  }

  private MemberEntity findMember(Long id) {
    return memberJpaRepository.findById(id).orElseThrow(
        EntityNotFoundException::new
    );
  }

}
