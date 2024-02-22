package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {

  private Long id;
  private String email;

}
