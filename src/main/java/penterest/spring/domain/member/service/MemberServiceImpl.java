package penterest.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.gif.dto.GifInfoDto;
import penterest.spring.domain.member.dto.*;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.entity.RefreshToken;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.domain.member.repository.RefreshTokenRepository;
import penterest.spring.global.exception.DuplicateEmailException;
import penterest.spring.global.exception.ValidationException;
import penterest.spring.global.jwt.TokenProvider;
import penterest.spring.global.security.util.SecurityUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public void signUp(MemberSignUpDto memberSignUpDto) throws ValidationException, DuplicateEmailException {
        Member member = memberSignUpDto.toEntity();

        member.encodePassword(passwordEncoder);

        if (memberRepository.existsByEmail(memberSignUpDto.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }
        memberRepository.save(member);
    }

    public TokenDto authenticateAndGenerateToken(LoginDto memberRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberRequestDto.getEmail(), memberRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return new TokenDto(jwt,"");
    }

    @Override
    public void updatePassword(String beforePW, String afterPW, String email) throws Exception {

        Member member = memberRepository.findByEmail(email);

        if (!member.matchPassword(passwordEncoder, beforePW)) {
            throw new Exception();
        }

        member.setPassword(passwordEncoder, afterPW);
    }

    @Override
    public void withdraw(String checkPassword, String email) throws Exception {
        Member member = memberRepository.findByEmail(email);

        if (!member.matchPassword(passwordEncoder, checkPassword)) {
            throw new Exception();
        }

        memberRepository.delete(member);
    }

    /**
     * 회원정보 가져오기
     */
    public MemberInfoDto getInfo(Long id) throws Exception {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new Exception());
        return new MemberInfoDto(findMember);
    }

    /**
     * 내정보 가져오기
     */
    public MemberInfoDto getMyInfo() {
        Member findMember = memberRepository.findByEmail(SecurityUtil.getLoginUserEmail());
        return new MemberInfoDto(findMember);
    }

    @Override
    public List<MemberGifDto> getGifInfoByEmail(String email) throws Exception {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            // Handle the case when member is not found
            return null; // or throw an exception
        }

        List<MemberGifDto> gifDtoList = member.getGifList().stream()
                .map(gif -> new MemberGifDto(gif.getId(), gif.getUrl(), gif.getCaption()))
                .collect(Collectors.toList());

        return gifDtoList;
    }


}
