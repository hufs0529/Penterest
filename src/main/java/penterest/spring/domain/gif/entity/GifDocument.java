package penterest.spring.domain.gif.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import penterest.spring.domain.comment.dto.CommentInfoDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.member.dto.MemberInfoDto;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.global.domain.BaseTimeEntity;

import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "gif_index")
public class GifDocument extends BaseTimeEntity {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Lob
    @Field(type = FieldType.Text)
    private String caption;

    @Field(type = FieldType.Text)
    private String url;

    public static GifDocument from(Gif gif) {
        return GifDocument.builder()
                .id(gif.getId())
                .url(gif.getUrl())
                .caption(gif.getCaption())
                .build();
    }

}
