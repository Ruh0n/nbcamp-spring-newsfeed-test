package com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.infrastructure;

import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.infrastructure.entity.HallOfFameEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallOfFameJpaRepository extends JpaRepository<HallOfFameEntity, Long> {

  List<HallOfFameEntity> findAllByOrderByVoteCountDesc();

}
