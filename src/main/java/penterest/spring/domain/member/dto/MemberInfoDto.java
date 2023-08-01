package penterest.spring.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;

import java.util.Set;

@Getter
public class MemberInfoDto {

    private String email;
    private String password;

    private Set<Authority> authorityDtoSet;

    @Builder
    public MemberInfoDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.authorityDtoSet = member.getAuthorities();
    }
}
