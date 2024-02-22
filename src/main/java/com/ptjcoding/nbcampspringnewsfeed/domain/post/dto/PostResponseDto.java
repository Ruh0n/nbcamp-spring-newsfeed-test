package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {

  private final Long postId;
  private final String nickname;
  private final String title;
  private final String content;
  private final Long agreeCount;
  private final Long disagreeCount;
  private final Boolean isAgree;
  private final List<CommentResponseDto> commentList;
  private final LocalDateTime createdDate;
  private final LocalDateTime updatedDate;
  private final LocalDateTime deletedDate;

  public static PostResponseDto fromPost(Post post, String nickname) {
    return PostResponseDto
        .builder()
        .postId(post.getPostId())
        .nickname(nickname)
        .title(post.getTitle())
        .content(post.getContent())
        .agreeCount(post.getAgreeCount())
        .disagreeCount(post.getDisagreeCount())
        .createdDate(post.getCreatedDate())
        .updatedDate(post.getUpdatedDate())
        .deletedDate(post.getDeletedDate())
        .build();
  }

  public static PostResponseDto fromPostDetail(
      Post post,
      String nickname,
      List<CommentResponseDto> commentList,
      Boolean isAgree
  ) {
    return PostResponseDto.builder()
        .postId(post.getPostId())
        .nickname(nickname)
        .title(post.getTitle())
        .content(post.getContent())
        .isAgree(isAgree)
        .commentList(commentList)
        .agreeCount(post.getAgreeCount())
        .disagreeCount(post.getDisagreeCount())
        .createdDate(post.getCreatedDate())
        .updatedDate(post.getPostUpdatedDate())
        .deletedDate(post.getDeletedDate())
        .build();
  }

}
