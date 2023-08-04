package penterest.spring.domain.gif.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.global.domain.BaseTimeEntity;

import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "gif")

public class GifDocument extends BaseTimeEntity {

    @Id
    private Long id;

    private Member writer;

    @Lob
    @Field(type = FieldType.Text)
    private String caption;

    private String url;

    private List<Comment> commentList = new ArrayList<>();


    public static GifDocument from(Gif gif) {
        return GifDocument.builder()
                .id(gif.getId())
                .url(gif.getUrl())
                .caption(gif.getCaption())
                .build();
    }

}
