package penterest.spring.domain.gif.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import penterest.spring.domain.comment.dto.CommentInfoDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.dto.MemberInfoDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GifInfoDto {

    private Long gifId;
    private String url;
    private String caption;

    private MemberInfoDto memberInfoDto;

    private List<CommentInfoDto> commentInfoDtoList;

    public GifInfoDto(Gif gif) {
        this.gifId = gif.getId();
        this.url = gif.getUrl();
        this.caption = gif.getCaption();

        this.memberInfoDto = new MemberInfoDto(gif.getWriter());


        /**
         * 댓글과 대댓글을 그룹짓기
         * post.getCommentList()는 댓글과 대댓글이 모두 조회된다.
         */
        Map<Comment, List<Comment>> commentListMap = gif.getCommentList().stream()
                .filter(comment -> comment.getParent() != null)
                .collect(Collectors.groupingBy(Comment::getParent));


        /**
         * 댓글과 대댓글을 통해 CommentInfoDto 생성
         */
        commentInfoDtoList = commentListMap.keySet().stream()

                .map(comment -> new CommentInfoDto(comment, commentListMap.get(comment)))
                .toList();

    }

}
