package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.PostJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final PostJpaRepository postJpaRepository;

  @Override
  @Transactional
  public Post createPost(PostRequestDto postRequestDto, Member member) {
    return postJpaRepository.save(PostEntity.of(postRequestDto, member.getId())).toModel();
  }

  @Override
  public List<Post> findPosts() {
    return postJpaRepository.findAllByOrderByCreatedDateDesc()
        .stream()
        .map(PostEntity::toModel)
        .toList();
  }

  @Override
  public Post findPostOrElseThrow(Long postId) {
    return postJpaRepository.findById(postId).orElseThrow(
        () -> new CustomRuntimeException(GlobalErrorCode.NOT_FOUND)
    ).toModel();
  }

  @Override
  public Post updatePost(Long postId, PostRequestDto postRequestDto) {
    PostEntity postEntity = postJpaRepository.findById(postId).orElseThrow(
        () -> new CustomRuntimeException(GlobalErrorCode.NOT_FOUND)
    );
    postEntity.update(postRequestDto);
    return postEntity.toModel();
  }

  @Override
  public void deletePost(Long postId) {
    postJpaRepository.deleteById(postId);
  }

  @Override
  public List<Post> findPostsByMemberId(Long memberId) {
    return postJpaRepository.findAllByMemberId(memberId)
        .stream()
        .map(PostEntity::toModel)
        .toList();
  }

  @Override
  public void deletePostsByMemberId(Long memberId) {
    postJpaRepository.deleteAllByMemberId(memberId);
  }

  @Override
  public void upAgreeCount(Long postId) {
    PostEntity postEntity = findPostEntityorElseThrow(postId);
    postEntity.upAgreeCount();
  }

  @Override
  public void downAgreeCount(Long postId) {
    PostEntity postEntity = findPostEntityorElseThrow(postId);
    postEntity.downAgreeCount();
  }

  @Override
  public void upDisagreeCount(Long postId) {
    PostEntity postEntity = findPostEntityorElseThrow(postId);
    postEntity.upDisagreeCount();
  }

  @Override
  public void downDisagreeCount(Long postId) {
    PostEntity postEntity = findPostEntityorElseThrow(postId);
    postEntity.downDisagreeCount();
  }

  public PostEntity findPostEntityorElseThrow(Long postId) {
    return postJpaRepository.findById(postId).orElseThrow(
        () -> new CustomRuntimeException(GlobalErrorCode.NOT_FOUND)
    );
  }

}
