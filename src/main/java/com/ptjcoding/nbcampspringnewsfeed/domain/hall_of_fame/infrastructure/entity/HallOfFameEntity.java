package com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.infrastructure.entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "hall_of_fame")
@NoArgsConstructor
@AllArgsConstructor
public class HallOfFameEntity {

  @Id
  private Long postId;
  private Long voteCount;

  public static HallOfFameEntity of(Post post) {
    Long voteCount = post.getAgreeCount() + post.getDisagreeCount();
    return new HallOfFameEntity(post.getPostId(), voteCount);
  }

  public void update(Long voteCount) {
    this.voteCount = voteCount;
  }

}
