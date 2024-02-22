package com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.infrastructrue.Entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.model.Bookmark;
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
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "bookmark")
@EntityListeners(AuditingEntityListener.class)
public class BookmarkEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookmarkId;
  @Column(nullable = false)
  @Setter
  private Long memberId;
  @Column(nullable = false)
  @Setter
  private Long postId;
  @Column(nullable = false)
  @CreatedDate
  private LocalDateTime markedDate;

  public static BookmarkEntity of(Long postId, Long memberId) {
    return BookmarkEntity
        .builder()
        .memberId(memberId)
        .postId(postId)
        .build();
  }

  public Bookmark toModel() {
    return Bookmark
        .builder()
        .bookmarkId(bookmarkId)
        .memberId(memberId)
        .postId(postId)
        .markedDate(markedDate)
        .build();
  }

}
