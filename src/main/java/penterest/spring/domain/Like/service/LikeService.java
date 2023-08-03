package penterest.spring.domain.Like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.Like.dto.LikeRequestDto;
import penterest.spring.domain.Like.entity.Like;
import penterest.spring.domain.Like.repository.LikeRepository;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final GifRepository gifRepository;

    @Transactional
    public void like(LikeRequestDto likeRequestDto) throws Exception {

        Member member = memberRepository.findById(likeRequestDto.getMemberId())
                .orElseThrow(()-> new Exception("Could not found member id : " + likeRequestDto.getMemberId()));

        Gif gif = gifRepository.findById(likeRequestDto.getGifId())
                .orElseThrow(()-> new Exception("Could not found member id : " + likeRequestDto.getGifId()));

        Like existingLike = likeRepository.findByMemberAndGif(member, gif);
        if (existingLike != null) {
            throw new Exception("Like already exists.");
        }

        Like like = Like.builder()
                .gif(gif)
                .member(member)
                .build();

        likeRepository.save(like);
    }

    @Transactional
    public void dislike(LikeRequestDto likeRequestDto) throws Exception {
        Member member = memberRepository.findById(likeRequestDto.getMemberId())
                .orElseThrow(() -> new Exception("Could not found member id : " + likeRequestDto.getMemberId()));

        Gif gif = gifRepository.findById(likeRequestDto.getGifId())
                .orElseThrow(() -> new Exception("Could not found member id : " + likeRequestDto.getGifId()));

        Like like = likeRepository.findByMemberAndGif(member, gif);
        likeRepository.delete(like);
    }
}
