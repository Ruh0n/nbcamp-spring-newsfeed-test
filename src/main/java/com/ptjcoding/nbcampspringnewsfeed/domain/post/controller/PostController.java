package com.ptjcoding.nbcampspringnewsfeed.domain.post.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<CommonResponseDto<PostResponseDto>> createPost(
      @Validated @RequestBody PostRequestDto postRequestDto,
      @RequestAttribute("member") Member member
  ) {
    return CommonResponseDto.ok("게시글 작성 성공",
        postService.createPost(postRequestDto, member.getId()));
  }

  @GetMapping
  public ResponseEntity<CommonResponseDto<List<PostResponseDto>>> getPosts() {
    return CommonResponseDto.ok("모든 게시글 조회 성공",
        postService.getPosts());
  }

  @GetMapping("/{postId}")
  public ResponseEntity<CommonResponseDto<PostResponseDto>> getPost(@PathVariable Long postId) {
    return CommonResponseDto.ok("게시글 단 건 조회 성공",
        postService.getPost(postId));
  }
}