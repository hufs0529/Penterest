package penterest.spring.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;

import java.util.List;
import java.util.Set;

@Getter
public class MemberInfoDto {

    private String email;
    private String password;
//    private List<Gif> gifList;
//    private List<Comment> commentList;
    private Set<Authority> authorityDtoSet;

    @Builder
    public MemberInfoDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.authorityDtoSet = member.getAuthorities();
//        this.gifList = member.getGifList();
//        this.commentList = member.getCommentList();
    }
}
