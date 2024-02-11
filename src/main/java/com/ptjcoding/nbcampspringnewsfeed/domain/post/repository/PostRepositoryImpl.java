package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.PostJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import jakarta.persistence.EntityNotFoundException;
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
  public List<Post> getPosts() {
    return postJpaRepository.findAllByOrderByCreatedDateDesc()
        .stream()
        .map(PostEntity::toModel)
        .toList();
  }

  @Override
  public Post getPost(Long postId) {
    return findByIdOrElseThrow(postId);
  }

  @Override
  public Post findByIdOrElseThrow(Long postId) {
    return postJpaRepository.findById(postId).orElseThrow(
        () -> new EntityNotFoundException("Post with id " + postId + " not found")
    ).toModel();
  }

  @Override
  public Post updatePost(Long postId, PostRequestDto postRequestDto) {
    PostEntity postEntity = postJpaRepository.findById(postId).orElseThrow(
        () -> new EntityNotFoundException("Post with id " + postId + " not found")
    );
    postEntity.setTitle(postRequestDto.getTitle());
    postEntity.setContent(postRequestDto.getContent());
    return postEntity.toModel();
  }

  @Override
  public void deletePost(Long postId) {
    postJpaRepository.deleteById(postId);
  }

  @Override
  public List<Post> getPostsByMemberId(Long memberId) {
    return postJpaRepository.findAllByMemberId(memberId)
        .stream()
        .map(PostEntity::toModel)
        .toList();
  }

  @Override
  public void deletePostsByMemberId(Long memberId) {
    postJpaRepository.deleteByMemberId(memberId);
  }
}