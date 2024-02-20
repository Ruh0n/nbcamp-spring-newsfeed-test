package com.ptjcoding.nbcampspringnewsfeed.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostRequestDto {

  @NotBlank
  @Length(max = 40)
  private String title;
  @NotBlank
  @Length(max = 500)
  private String content;

}
