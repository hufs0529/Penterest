package penterest.spring.domain.comment.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends Serializers.Base {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gif_id")
    private Gif gif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Lob
    @Column(nullable = false)
    private String content;

    private boolean isRemoved = false;

    // 부모 댓글 삭제해도 자식 댓그 존재
    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();


    public void confirmWriter(Member writer) {
        this.writer = writer;
        writer.addComment(this);
    }

    public void confirmGif(Gif gif) {
        this.gif = gif;
        gif.addComment(this);
    }

    public void confirmParent(Comment parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child) {
        childList.add(child);
    }

    public void remove() {
        this.isRemoved = true;
    }

    @Builder
    public Comment( Member writer, Gif post, Comment parent, String content) {
        this.writer = writer;
        this.gif = post;
        this.parent = parent;
        this.content = content;
        this.isRemoved = false;
    }


}
