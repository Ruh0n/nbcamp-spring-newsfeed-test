package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.model.Bookmark;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository {

  Bookmark createBookmark(Long postId, Long memberId);

  List<Bookmark> findBookmarksByMemberId(Long memberId);

  Optional<Bookmark> findBookmarkByPostIdAndMemberId(Long postId, Long memberId);

  void deleteBookmark(Long postId, Long memberId);

  void deleteBookmarksByPostId(Long postId);

  void deleteBookmarksByMemberId(Long memberId);

}
