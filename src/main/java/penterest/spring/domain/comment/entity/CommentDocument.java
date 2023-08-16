package penterest.spring.domain.comment.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import penterest.spring.domain.gif.dto.BriefGifInfo;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;
import penterest.spring.global.domain.BaseTimeEntity;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "comment_index")
public class CommentDocument extends BaseTimeEntity {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Text)
    private Long gifId;
    public static CommentDocument from(Comment comment) {
        return CommentDocument.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .gifId(comment.getGif().getId())
                .build();
    }

}
