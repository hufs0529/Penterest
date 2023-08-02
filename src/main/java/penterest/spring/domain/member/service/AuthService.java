package penterest.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.member.dto.*;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.entity.RefreshToken;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.domain.member.repository.RefreshTokenRepository;
import penterest.spring.global.jwt.TokenProvider;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
//        if (memberRepository.existsByEmail(memberRequestDto.getEmail())){
//            throw new RuntimeException("이미 가입된 유저입니다");
//        }
//
//        Member member = memberRequestDto.toMember(passwordEncoder);
//        return MemberResponseDto.of(memberRepository.save(member));
//    }

//    @Transactional
//    public TokenDto login(MemberSignUpDto memberSignUpDto) {
//        UsernamePasswordAuthenticationToken authenticationToken = memberSignUpDto.toAuthentication();
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        TokenDto tokenDto = tokenProvider.generateToken(authentication);
//
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(authentication.getName())
//                .value(tokenDto.getRefreshToken())
//                .build();
//
//        refreshTokenRepository.save(refreshToken);
//
//        return tokenDto;
//    }

//    @Transactional
//    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
//        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())){
//            throw new RuntimeException("Refresh Token이 유효하지 않습니다");
//        }
//
//        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
//
//        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
//                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
//
//        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
//            throw new RuntimeException("토큰 정보가 일치하지 않습니다");
//        }
//
//        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
//
//        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
//        refreshTokenRepository.save(newRefreshToken);
//
//        return tokenDto;
//    }

}
