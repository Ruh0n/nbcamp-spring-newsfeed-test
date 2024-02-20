package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue.Entity.BookmarkEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {

  List<BookmarkEntity> findAllByMemberId(Long memberId);

  Optional<BookmarkEntity> findBookmarkEntityByPostIdAndMemberId(Long postId, Long memberId);

  void deleteByPostIdAndMemberId(Long postId, Long memberId);

  void deleteByPostId(Long postId);

  void deleteByMemberId(Long memberId);

}
