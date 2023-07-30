package penterest.spring.domain.gif.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.gif.dto.GifInfoDto;
import penterest.spring.domain.gif.dto.GifSaveDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.global.security.util.SecurityUtil;

import java.util.Collection;

import static penterest.spring.global.security.util.SecurityUtil.getAuthorities;

@Service
@Transactional
@RequiredArgsConstructor
public class GifServiceImpl implements  GifService{

    private final GifRepository gifRepository;
    private final MemberRepository memberRepository;


    @Override
    public void save(GifSaveDto gifSaveDto) {
        Gif gif = gifSaveDto.toEntity();
        if (checkAuthority(gif)) {
            gif.confirmWriter(memberRepository.findByEmail(SecurityUtil.getLoingUserEmail()));
        }else {
            gif.confirmWriter(null);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Gif gif = gifRepository.findById(id).orElseThrow(()->
                new Exception());
        Collection<? extends GrantedAuthority> authorities = getAuthorities();

        if (checkAuthority(gif) ||  "ADMIN".equals(authorities)) {
            gifRepository.delete(gif);
        }

    }

    private boolean checkAuthority(Gif gif) {
        String loginUserEmail = SecurityUtil.getLoingUserEmail();
        Member writer = gif.getWriter();
        return writer != null && writer.getEmail().equals(loginUserEmail);
    }

    @Override
    public GifInfoDto getGifInfo(Long id) throws Exception {

        return new GifInfoDto(gifRepository.findWriterById(id)
                .orElseThrow( ()-> new Exception()));
    }

}
