package com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

  List<PostEntity> findAllByOrderByCreatedDateDesc();

  List<PostEntity> findAllByMemberId(Long memberId);

  void deleteAllByMemberId(Long memberId);

}
