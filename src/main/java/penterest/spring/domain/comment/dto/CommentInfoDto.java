package penterest.spring.domain.comment.dto;

import lombok.Data;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.member.dto.MemberInfoDto;

import java.util.List;

@Data
public class CommentInfoDto {

    private final static String DEFAULT_DELETE_MESSAGE = "삭제된 댓글입니다";

    private Long gifId; // 댓글 달린 gif의 id

    private Long commentId; // 해당 댓글 id
    private String content;
    private boolean isRemoved;

    private MemberInfoDto writerDto;

    private List<ReCommentInfoDto> reCommentInfoDtoList; //대댓글 정보

    public CommentInfoDto(Comment comment, List<Comment> reCommentList) {
        this.gifId = comment.getGif().getId();
        this.commentId = comment.getId();

        this.content = comment.getContent();

        if(comment.isRemoved()) {
            this.content = DEFAULT_DELETE_MESSAGE;
        }

        this.isRemoved = comment.isRemoved();
        this.writerDto = new MemberInfoDto(comment.getWriter());

        this.reCommentInfoDtoList = reCommentList.stream().map(ReCommentInfoDto::new).toList();
    }
}
