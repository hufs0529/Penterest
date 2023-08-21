package penterest.spring.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.entity.Role;

import java.util.List;
import java.util.Set;

@Getter
public class MemberInfoDto {

    private String email;
    private String password;
    private Role role;

    @Builder
    public MemberInfoDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.role = member.getRole();
    }
}
