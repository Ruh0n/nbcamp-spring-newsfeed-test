package com.ptjcoding.nbcampspringnewsfeed.domain.comment.etc;

import lombok.Getter;

@Getter
public enum CommentRequestError {
  CONTENT_BLANK(CommentRequestErrorMessage.CONTENT_BLANK),
  CONTENT_MAX_LENGTH(CommentRequestErrorMessage.CONTENT_MAX_LENGTH),
  POSTID_BLANK(CommentRequestErrorMessage.POSTID_BLANK),
  POSTID_POSITIVE(CommentRequestErrorMessage.POSTID_POSITIVE),
  PARENTCOMMENTIDSTRING_POSITIVE(CommentRequestErrorMessage.PARENTCOMMENTIDSTRING_POSITIVE),
  ;


  final String message;

  CommentRequestError(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return message;
  }

  public static class CommentRequestErrorMessage {

    private static final String CONTENT = "댓글 내용은 ";
    private static final String POSTID = "게시글 Id는 ";
    private static final String PARENTCOMMENTIDSTRING = "부모 댓글의 Id는";

    private static final String BLANK = "필수입니다.";
    public static final String CONTENT_BLANK = CONTENT + BLANK;
    public static final String POSTID_BLANK = POSTID + BLANK;
    private static final String MAX_LENGTH = "500글자를 초과할 수 없습니다.";
    public static final String CONTENT_MAX_LENGTH = CONTENT + MAX_LENGTH;
    private static final String POSITIVE = "양수 값이어야 합니다.";
    public static final String POSTID_POSITIVE = POSTID + POSITIVE;
    public static final String PARENTCOMMENTIDSTRING_POSITIVE = PARENTCOMMENTIDSTRING + POSITIVE;

  }
}
