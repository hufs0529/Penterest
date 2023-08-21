package penterest.spring.domain.Like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.Like.dto.LikeRequestDto;
import penterest.spring.domain.Like.entity.Like;
import penterest.spring.domain.Like.repository.LikeRepository;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.entity.Role;
import penterest.spring.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final GifRepository gifRepository;

    private String checkAuthority(Gif gif) {
        //String loginUserEmail = SecurityUtil.getLoginUserEmail();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();

        //String writer = gif.getWriter().getEmail();
        return email;
    }

    @Transactional
    public String like(LikeRequestDto likeRequestDto) throws Exception {

        Member member = memberRepository.findById(likeRequestDto.getMemberId())
                .orElseThrow(() -> new Exception(("Could not found member id : " + likeRequestDto.getMemberId())));
        Gif gif = gifRepository.findById(likeRequestDto.getGifId())
                .orElseThrow(()-> new Exception("Could not found gif id : " + likeRequestDto.getGifId()));

        if (member.getEmail().equals(checkAuthority(gif))) {
            Like existingLike = likeRepository.findByMemberAndGif(member, gif);

            if (existingLike != null) {
                throw new Exception("Like already exists.");
            }
            Like like = Like.builder()
                    .gif(gif)
                    .member(member)
                    .build();

            likeRepository.save(like);
        }else
            throw new Exception("인가된 유저가 아닙니다");

        return member.getEmail() + " 유저가 " + gif.getId() + " 게시물을 좋아합니다";
    }

    @Transactional
    public String dislike(LikeRequestDto likeRequestDto) throws Exception {
        Member member = memberRepository.findById(likeRequestDto.getMemberId())
                .orElseThrow(() -> new Exception(("Could not found member id : " + likeRequestDto.getMemberId())));
        Gif gif = gifRepository.findById(likeRequestDto.getGifId())
                .orElseThrow(() -> new Exception("Could not found gif id : " + likeRequestDto.getGifId()));

        if (member.getEmail().equals(checkAuthority(gif))) {
            Like like = likeRepository.findByMemberAndGif(member, gif);
            likeRepository.delete(like);
        }else {
            throw new Exception("인가된 유저가 아닙니다");
        }
        return member.getEmail() + " 유저가 " + gif.getId() + " 게시물을 좋아요를 취소했습니다";
    }
}
