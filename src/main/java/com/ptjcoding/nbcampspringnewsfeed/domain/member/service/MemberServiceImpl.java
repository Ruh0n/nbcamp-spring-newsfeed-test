package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import static com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole.USER;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository.BlackListRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository.BookmarkRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.repository.HallOfFameRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.NicknameUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.NicknameUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberInfoDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.NicknameChangeDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final VoteRepository voteRepository;
  private final BookmarkRepository bookmarkRepository;
  private final HallOfFameRepository hallOfFameRepository;
  private final BlackListRepository blackListRepository;
  private final JwtProvider jwtProvider;

  @Override
  public MemberResponseDto signup(SignupRequestDto dto) {
    MemberSignupDto memberSignupDto = new MemberSignupDto(dto.getEmail(), dto.getNickname(),
        dto.getPassword(),
        dto.getCheckPassword());

    if (memberRepository.checkEmail(memberSignupDto.getEmail())) {
      throw new CustomRuntimeException(GlobalErrorCode.ALREADY_EXIST);
    }
    memberRepository.register(memberSignupDto);

    return MemberResponseDto.builder()
        .email(dto.getEmail())
        .nickname(dto.getNickname())
        .build();
  }

  @Override
  public void login(LoginRequestDto dto, HttpServletResponse response) {
    if (blackListRepository.checkEmail(dto.getEmail())) {
      throw new CustomRuntimeException(GlobalErrorCode.UNAUTHORIZED);
    }

    if (!memberRepository.checkEmail(dto.getEmail())) {
      throw new CustomRuntimeException(GlobalErrorCode.NOT_FOUND);
    }

    Member member = memberRepository.checkPassword(dto);

    String accessToken = jwtProvider.generateAccessToken(member.getId(), USER.getAuthority());
    String refreshToken = jwtProvider.generateRefreshToken(member.getId(), USER.getAuthority());

    jwtProvider.addAccessTokenToCookie(accessToken, response);
    jwtProvider.addRefreshTokenToCookie(refreshToken, response);
  }

  @Override
  @Transactional
  public void logout(HttpServletRequest request, Member member) {
    jwtProvider.expireToken(request, member.getId());
  }

  @Override
  public void delete(Long memberId) {
    deleteHallOfFame(memberId);
    voteRepository.deleteVotesByMemberId(memberId);
    bookmarkRepository.deleteBookmarksByMemberId(memberId);
    commentRepository.deleteCommentsByMemberId(memberId);
    deletePosts(memberId);
    memberRepository.deleteMember(memberId);
  }

  @Override
  @Transactional(readOnly = true)
  public MemberInfoDto memberInfo(Long memberId) {
    Member member = memberRepository.findMemberOrElseThrow(memberId);
    List<Post> postList = postRepository.findPostsByMemberId(memberId);
    List<Comment> commentList = commentRepository.findCommentsByMemberId(memberId);

    return MemberInfoDto.of(member, postList, commentList);
  }

  @Override
  public NicknameChangeDto updateMemberName(
      Member member,
      NicknameUpdateRequestDto dto
  ) {
    Member changeMember = memberRepository.updateMember(member.getId(),
        NicknameUpdateDto.of(dto));
    return NicknameChangeDto.of(member.getNickname(), changeMember.getNickname());
  }

  private void deleteHallOfFame(Long memberId) {
    voteRepository.findVotesByMemberId(memberId).forEach(
        vote -> {
          Post post = postRepository.findPostOrElseThrow(vote.getPostId());
          hallOfFameRepository.updateTable(vote.getPostId(), post.getVoteCount() - 1);
        }
    );

    postRepository.findPostsByMemberId(memberId)
        .forEach(post -> hallOfFameRepository.deleteHallOfFame(post.getPostId()));
  }

  private void deletePosts(Long memberId) {
    postRepository.findPostsByMemberId(memberId)
        .forEach(post -> {
          voteRepository.deleteVotesByPostId(post.getPostId());
          commentRepository.deleteCommentsByPostId(post.getPostId());
        });

    postRepository.deletePostsByMemberId(memberId);
  }

}
