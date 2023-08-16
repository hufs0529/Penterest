package penterest.spring.domain.comment.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import penterest.spring.domain.comment.entity.CommentDocument;
import penterest.spring.domain.gif.dto.GitESDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.repository.GifRepository;

@Setter
@Getter
public class CommentESDto {

    @Autowired
    private GifRepository gifRepository;

    private String id;
    private String content;
    private GitESDto gifDto;


    public static CommentESDto fromCommentDocument(CommentDocument commentDocument, Gif gif) {
        CommentESDto dto = new CommentESDto();
        dto.setId(commentDocument.getId());
        dto.setContent(commentDocument.getContent());

        Long gifId = commentDocument.getGif_id();
        if (gifId != null && gif != null) {
            GitESDto gifDTO = GitESDto.fromGif(gif);
            dto.setGifDto(gifDTO);
        }
        return dto;
    }
}
