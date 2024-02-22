package com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

  Optional<MemberEntity> findByEmail(String email);

}
