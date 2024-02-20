package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.dto.BookmarkResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BookmarkService {

  BookmarkResponseDto createBookmark(Long postId, Long memberId);

  List<BookmarkResponseDto> getBookmarksByMemberId(Long memberId);

  void deleteBookmark(Long postId, Long memberId);

}
