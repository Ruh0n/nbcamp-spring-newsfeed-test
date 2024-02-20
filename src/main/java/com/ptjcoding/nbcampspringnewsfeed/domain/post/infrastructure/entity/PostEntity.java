package com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.entity.Timestamped;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "post")
@SQLDelete(sql = "update post set deleted_date = NOW() where post_id = ?")
@SQLRestriction(value = "deleted_date is NULL")
@EntityListeners(AuditingEntityListener.class)
public class PostEntity extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;
  @Column(nullable = false)
  private Long memberId;
  @Column(nullable = false, length = 40)
  private String title;
  @Column(nullable = false, length = 500)
  private String content;
  @Column(nullable = false)
  private Long agreeCount;
  @Column(nullable = false)
  private Long disagreeCount;
  @Column(name = "post_updated_date")
  private LocalDateTime postUpdatedDate;

  public static PostEntity of(PostRequestDto postRequestDto, Long memberId) {
    return PostEntity
        .builder()
        .memberId(memberId)
        .title(postRequestDto.getTitle())
        .content(postRequestDto.getContent())
        .agreeCount(0L)
        .disagreeCount(0L)
        .build();
  }

  public void update(PostRequestDto postRequestDto) {
    this.title = postRequestDto.getTitle();
    this.content = postRequestDto.getContent();
    this.postUpdatedDate = LocalDateTime.now();
  }

  public void upAgreeCount() {
    ++this.agreeCount;
  }

  public void downAgreeCount() {
    --this.agreeCount;
  }

  public void upDisagreeCount() {
    ++this.disagreeCount;
  }

  public void downDisagreeCount() {
    --this.disagreeCount;
  }

  public Post toModel() {
    return Post
        .builder()
        .postId(postId)
        .memberId(memberId)
        .title(title)
        .content(content)
        .agreeCount(agreeCount)
        .disagreeCount(disagreeCount)
        .createdDate(this.getCreatedDate())
        .updatedDate(this.getPostUpdatedDate())
        .deletedDate(this.getDeletedDate())
        .build();
  }

}
