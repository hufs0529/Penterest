package penterest.spring.domain.comment.dto;

import lombok.Data;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.member.dto.MemberInfoDto;

@Data
public class ReCommentInfoDto {

    private final static String DEFAULT_DELETE_MESSAGE = "삭제된 댓글입니다";

    private Long gifId;
    private Long parentId;


    private Long reCommentId;
    private String content;
    private boolean isRemoved;


    private String email;

    public ReCommentInfoDto(Comment reComment) {
        this.gifId = reComment.getGif().getId();
        this.parentId = reComment.getParent().getId();
        this.reCommentId = reComment.getId();
        this.content = reComment.getContent();

        if(reComment.isRemoved()) {
            this.content = DEFAULT_DELETE_MESSAGE;
        }

        this.isRemoved = reComment.isRemoved();
        this.email = reComment.getWriter().getEmail();

    }
}
