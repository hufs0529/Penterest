package penterest.spring.domain.gif.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.gif.dto.GifInfoDto;
import penterest.spring.domain.gif.dto.GifSaveDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.global.security.util.SecurityUtil;


@Service
@Transactional
@RequiredArgsConstructor
public class GifServiceImpl implements  GifService{

    private final GifRepository gifRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void save(GifSaveDto gifSaveDto) {
        Gif gif = gifSaveDto.toEntity();
        Member defaultMember = memberRepository.findByEmail("user@example.com");

        if (checkAuthority(gif)) {
            // Gif 엔티티를 저장하기 전에 Member 엔티티를 먼저 저장해야 합니다.
            Member member = memberRepository.findByEmail(gif.getWriter().getEmail());
            member.encodePassword(passwordEncoder);
            gif.confirmWriter(memberRepository, member); // memberRepository를 인자로 전달
        } else {
            memberRepository.save(defaultMember);
            gif.confirmWriter(memberRepository, defaultMember); // memberRepository를 인자로 전달
        }
        gifRepository.save(gif);
    }


    @Override
    public void delete(Long id) throws Exception {
        Gif gif = gifRepository.findById(id).orElseThrow(()->
                new Exception());

        if (checkAuthority(gif) || gif.getWriter().getAuthorities().equals("NORMAL") ) {
            gifRepository.delete(gif);
        }else {
            throw new Exception("인가된 유저가 아닙니다");
        }
    }


    private boolean checkAuthority(Gif gif) {
        String loginUserEmail = SecurityUtil.getLoginUserEmail();
        Member writer = gif.getWriter();
        return writer != null && writer.getEmail().equals(loginUserEmail);
    }

    @Override
    public GifInfoDto getGifInfo(Long id) throws Exception {

        return new GifInfoDto(gifRepository.findWriterById(id)
                .orElseThrow( ()-> new Exception()));
    }

}
