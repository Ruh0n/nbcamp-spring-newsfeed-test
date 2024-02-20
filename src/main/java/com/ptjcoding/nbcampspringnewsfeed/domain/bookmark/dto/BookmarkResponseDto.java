package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.model.Bookmark;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookmarkResponseDto {

  private final Long bookmarkId;
  private final Long memberId;
  private final Long postId;
  private final String title;
  private final LocalDateTime markedDate;

  public static BookmarkResponseDto from(Bookmark bookmark, String title) {
    return BookmarkResponseDto
        .builder()
        .bookmarkId(bookmark.getBookmarkId())
        .memberId(bookmark.getMemberId())
        .postId(bookmark.getPostId())
        .title(title)
        .markedDate(bookmark.getMarkedDate())
        .build();
  }

}
