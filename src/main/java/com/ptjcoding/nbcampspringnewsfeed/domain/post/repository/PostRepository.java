package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import java.util.List;

public interface PostRepository {

  Post createPost(PostRequestDto postRequestDto, Member member);

  List<Post> findPosts();

  Post findPostOrElseThrow(Long postId);

  Post updatePost(Long postId, PostRequestDto postRequestDto);

  void deletePost(Long postId);

  List<Post> findPostsByMemberId(Long memberId);

  void deletePostsByMemberId(Long memberId);

  void upAgreeCount(Long postId);

  void downAgreeCount(Long postId);

  void upDisagreeCount(Long postId);

  void downDisagreeCount(Long postId);

  PostEntity findPostEntityorElseThrow(Long postId);

}
