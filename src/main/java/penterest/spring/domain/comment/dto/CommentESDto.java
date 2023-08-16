package penterest.spring.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;
import penterest.spring.domain.comment.entity.CommentDocument;
import penterest.spring.domain.gif.dto.GitESDto;

@Setter
@Getter
public class CommentESDto {

    private Long id;
    private String content;
    private GitESDto gif;

    public static CommentESDto fromCommentDocument(CommentDocument commentDocument) {
        CommentESDto dto = new CommentESDto();
        dto.setId(commentDocument.getId());
        dto.setContent(commentDocument.getContent());

        GitESDto gifDTO = new GitESDto();
        gifDTO.setId(commentDocument.getGifId()); // 이 부분을 commentDocument의 gifId에서 실제 Gif 객체로 변환하는 로직으로 수정
        dto.setGif(gifDTO);

        return dto;
    }
}
