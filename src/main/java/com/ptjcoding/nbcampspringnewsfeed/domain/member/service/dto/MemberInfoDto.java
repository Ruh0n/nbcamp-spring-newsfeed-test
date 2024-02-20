package com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {

  private String nickname;
  private String email;
  private List<Post> postList;
  private List<Comment> commentList;

  public static MemberInfoDto of(Member member, List<Post> postList, List<Comment> commentList) {
    return MemberInfoDto.builder()
        .nickname(member.getNickname())
        .email(member.getEmail())
        .postList(postList)
        .commentList(commentList)
        .build();
  }

}
