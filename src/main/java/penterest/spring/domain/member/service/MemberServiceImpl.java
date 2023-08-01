package penterest.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.member.dto.MemberInfoDto;
import penterest.spring.domain.member.dto.MemberSignUpDto;
import penterest.spring.domain.member.dto.TokenDto;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.entity.RefreshToken;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.domain.member.repository.RefreshTokenRepository;
import penterest.spring.global.jwt.TokenProvider;
import penterest.spring.global.security.util.SecurityUtil;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


//    public Member getOrCreateDefaultMember() {
//        String defaultEmail = "default@example.com";
//        Member existingMember = memberRepository.findByEmail(defaultEmail);
//
//        if (existingMember != null) {
//            return existingMember;
//        } else {
//            Member newMember = new Member();
//            newMember.setEmail(defaultEmail);
//            newMember.setPassword("default1234!");
//            newMember.setAuthorities();
//            return memberRepository.save(newMember);
//        }
//    }

    @Override
    @Transactional
    public void signUp(MemberSignUpDto memberSignUpDto) throws Exception {
        Member member = memberSignUpDto.toEntity();

        member.encodePassword(passwordEncoder);

        if (memberRepository.existsByEmail(memberSignUpDto.getEmail())) {
            throw new Exception();
        }
        memberRepository.save(member);

    }

    @Override
    public void updatePassword(String beforePW, String afterPW, String email) throws Exception {

        Member member = memberRepository.findByEmail(email);

        if(!member.matchPassword(passwordEncoder, beforePW)){
            throw new Exception();
        }

        member.setPassword(passwordEncoder, afterPW);
    }

    @Override
    public void withdraw(String checkPassword, String email) throws Exception {
        Member member = memberRepository.findByEmail(email);

        if(!member.matchPassword(passwordEncoder, checkPassword)) {
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
    public MemberInfoDto getMyInfo(){
        Member findMember = memberRepository.findByEmail(SecurityUtil.getLoginUserEmail());
        return new MemberInfoDto(findMember);
    }

}
