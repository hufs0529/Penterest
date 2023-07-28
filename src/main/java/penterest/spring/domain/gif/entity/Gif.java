package penterest.spring.domain.gif.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.global.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "gif")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Gif extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gif_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member writer;

    @Lob
    private String caption;

    private String url;

    // 게시글 삭제하면 달려있는 댓글 모두 삭제
    @OneToMany(mappedBy = "gif", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        // gif의 writer설정은 gif에서 함
        commentList.add(comment);
    }

    public void confirmWriter(Member writer) {
        this.writer = writer;
        writer.addGif(this);
    }



}
