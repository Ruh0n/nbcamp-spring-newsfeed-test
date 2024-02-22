package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateDto {

  private String content;

  public static CommentUpdateDto of(CommentUpdateRequestDto requestDto) {
    return new CommentUpdateDto(requestDto.getContent());
  }

}
