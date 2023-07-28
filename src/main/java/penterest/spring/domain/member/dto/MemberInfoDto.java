package penterest.spring.domain.member.dto;

import lombok.Builder;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;

public class MemberInfoDto {

    private String email;
    private String password;

    private Authority authority;

    @Builder
    public MemberInfoDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.authority = member.getAuthority();
    }
}
