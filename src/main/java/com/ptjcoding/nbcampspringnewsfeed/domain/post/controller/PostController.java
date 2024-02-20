package com.ptjcoding.nbcampspringnewsfeed.domain.post.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.service.PostService;
import com.ptjcoding.nbcampspringnewsfeed.global.enums.GlobalSuccessCode;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    return CommonResponseDto.ok(GlobalSuccessCode.CREATE,
        postService.createPost(postRequestDto, member.getId()));
  }

  @GetMapping
  public ResponseEntity<CommonResponseDto<List<PostResponseDto>>> getPosts() {
    return CommonResponseDto.ok(GlobalSuccessCode.SEARCH,
        postService.getPosts());
  }

  @GetMapping("/{postId}")
  public ResponseEntity<CommonResponseDto<PostResponseDto>> getPost(
      @PathVariable Long postId,
      @RequestAttribute Member member
  ) {
    return CommonResponseDto.ok(GlobalSuccessCode.SEARCH,
        postService.getPost(postId, member.getId()));
  }

  @GetMapping("/hallOfFame")
  public ResponseEntity<CommonResponseDto<List<PostResponseDto>>> getHallOfFame() {
    return CommonResponseDto.ok(GlobalSuccessCode.SEARCH,
        postService.getHallOfFame());
  }

  @PutMapping("/{postId}")
  public ResponseEntity<CommonResponseDto<PostResponseDto>> updatePost
      (
          @PathVariable Long postId,
          @Valid @RequestBody PostRequestDto postRequestDto,
          @RequestAttribute("member") Member member
      ) {
    return CommonResponseDto.ok(GlobalSuccessCode.UPDATE,
        postService.updatePost(postId, postRequestDto, member.getId()));
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<CommonResponseDto<PostResponseDto>> deletePost
      (
          @PathVariable Long postId,
          @RequestAttribute("member") Member member
      ) {
    postService.deletePost(postId, member.getId());
    return CommonResponseDto.ok(GlobalSuccessCode.DELETE, null);
  }

}
