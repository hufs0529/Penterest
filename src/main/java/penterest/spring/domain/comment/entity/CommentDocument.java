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
import java.time.LocalDateTime;
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
    private String id;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Long)
    private Long gif_id;

    @Field(type = FieldType.Date)
    private LocalDateTime createdDate;

    @Field(type = FieldType.Date)
    private LocalDateTime modifiedDate;
    public static CommentDocument from(Comment comment) {
        return CommentDocument.builder()
                .id(String.valueOf(comment.getId()))
                .content(comment.getContent())
                .gif_id(comment.getGif().getId())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }

}
