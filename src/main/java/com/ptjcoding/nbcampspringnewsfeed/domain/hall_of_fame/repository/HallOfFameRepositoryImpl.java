package com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.infrastructure.HallOfFameJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.infrastructure.entity.HallOfFameEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HallOfFameRepositoryImpl implements HallOfFameRepository {

  private final HallOfFameJpaRepository hallOfFameJpaRepository;

  @Override
  public List<Long> getTop3PostIdList() {
    return hallOfFameJpaRepository.findAllByOrderByVoteCountDesc()
        .stream()
        .map(HallOfFameEntity::getPostId)
        .toList();
  }

  @Override
  public void updateTable(Long postId, Long voteCount) {
    List<HallOfFameEntity> entities = hallOfFameJpaRepository.findAllByOrderByVoteCountDesc();
    final int MAX_SIZE = 3;

    Optional<HallOfFameEntity> existingEntity = entities.stream()
        .filter(entity -> postId.equals(entity.getPostId()))
        .findFirst();

    if (existingEntity.isPresent()) {
      existingEntity.get().update(voteCount);
      return;
    }

    if (entities.size() < MAX_SIZE) {
      hallOfFameJpaRepository.save(new HallOfFameEntity(postId, voteCount));
      return;
    }

    HallOfFameEntity minEntity = entities.get(2);
    if (voteCount < minEntity.getVoteCount()) {
      return;
    }

    hallOfFameJpaRepository.delete(minEntity);
    hallOfFameJpaRepository.save(new HallOfFameEntity(postId, voteCount));
  }

  @Override
  public void deleteHallOfFame(Long postId) {
    Optional<HallOfFameEntity> hallOfFameEntity = hallOfFameJpaRepository.findById(postId);

    if (hallOfFameEntity.isEmpty()) {
      return;
    }

    hallOfFameJpaRepository.delete(hallOfFameEntity.get());
  }

}
