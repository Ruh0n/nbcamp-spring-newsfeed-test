package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import java.util.Optional;

public interface VoteService {

  Vote createVote(VoteCreateRequestDto requestDto);

  Optional<Vote> getVoteByMemberIdAndPostId(Long memberId, Long postId);

  Vote updateVote(Long voteId, VoteUpdateRequestDto requestDto);

  void deleteVote(Long voteId);

}
