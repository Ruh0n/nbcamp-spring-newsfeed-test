package com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.entity;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.entity.Timestamped;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.NicknameUpdateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@Table(name = "member")
@SQLDelete(sql = "update member set deleted_date = NOW() where id = ?")
@SQLRestriction(value = "deleted_date is NULL")
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 40)
  private String email;

  @Column(nullable = false, length = 15)
  private String nickname;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, length = 10)
  @Enumerated(value = EnumType.STRING)
  private MemberRole role;

  public static MemberEntity of(String email, String nickname, String password, MemberRole role) {

    return MemberEntity.builder()
        .email(email)
        .nickname(nickname)
        .password(password)
        .role(role)
        .build();
  }

  public void update(NicknameUpdateDto dto) {
    this.nickname = dto.getNickname();
  }

  public boolean isCurrentName(String nickname) {
    return this.nickname.equals(nickname);
  }

  public Member toModel() {

    return Member.builder()
        .id(id)
        .email(email)
        .nickname(nickname)
        .password(password)
        .role(role)
        .build();
  }

}
