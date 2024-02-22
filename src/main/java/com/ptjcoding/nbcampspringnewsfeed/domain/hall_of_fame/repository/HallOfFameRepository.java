package com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface HallOfFameRepository {

  List<Long> getTop3PostIdList();

  void updateTable(Long postId, Long voteCount);

  void deleteHallOfFame(Long postId);

}
