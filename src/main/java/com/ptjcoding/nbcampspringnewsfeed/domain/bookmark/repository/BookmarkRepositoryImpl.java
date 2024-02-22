package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue.BookmarkJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue.Entity.BookmarkEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.model.Bookmark;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

  private final BookmarkJpaRepository bookmarkJpaRepository;

  @Override
  public Bookmark createBookmark(Long postId, Long memberId) {
    return bookmarkJpaRepository.save(BookmarkEntity.of(postId, memberId)).toModel();
  }

  @Override
  public Optional<Bookmark> findBookmarkByPostIdAndMemberId(Long postId, Long memberId) {
    return bookmarkJpaRepository.findBookmarkEntityByPostIdAndMemberId(postId, memberId)
        .map(BookmarkEntity::toModel);
  }

  @Override
  public List<Bookmark> findBookmarksByMemberId(Long memberId) {
    return bookmarkJpaRepository.findAllByMemberId(memberId)
        .stream()
        .map(BookmarkEntity::toModel)
        .toList();
  }

  @Override
  public void deleteBookmark(Long postId, Long memberId) {
    Optional<Bookmark> bookmark = findBookmarkByPostIdAndMemberId(postId, memberId);
    if (bookmark.isEmpty()) {
      throw new CustomRuntimeException(GlobalErrorCode.NOT_FOUND);
    }
    bookmarkJpaRepository.deleteByPostIdAndMemberId(postId, memberId);
  }

  @Override
  public void deleteBookmarksByPostId(Long postId) {
    bookmarkJpaRepository.deleteByPostId(postId);
  }

  @Override
  public void deleteBookmarksByMemberId(Long memberId) {
    bookmarkJpaRepository.deleteByMemberId(memberId);
  }

}
