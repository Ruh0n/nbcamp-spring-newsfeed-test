package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure.entity.BlackListEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListJpaRepository extends JpaRepository<BlackListEntity, Long> {

  Optional<BlackListEntity> findByEmail(String email);

}
