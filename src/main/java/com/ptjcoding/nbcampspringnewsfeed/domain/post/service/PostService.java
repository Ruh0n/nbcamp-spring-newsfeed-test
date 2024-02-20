package com.ptjcoding.nbcampspringnewsfeed.domain.post.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import java.util.List;

public interface PostService {

  PostResponseDto createPost(PostRequestDto postRequestDto, Long memberId);

  List<PostResponseDto> getPosts();

  PostResponseDto getPost(Long postId, Long memberId);

  List<PostResponseDto> getHallOfFame();

  PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, Long memberId);

  void deletePost(Long postId, Long memberId);

}
