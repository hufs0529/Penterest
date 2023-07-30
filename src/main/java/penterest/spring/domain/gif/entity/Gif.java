package penterest.spring.domain.gif.entity;

import lombok.*;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.member.entity.Authority;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "writer_id")
    private Member writer;

    @Lob
    private String caption;

    private String url;

    // 게시글 삭제하면 달려있는 댓글 모두 삭제
    @OneToMany(mappedBy = "gif", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Gif(String caption, String url) {
        this.caption = caption;
        this.url = url;

        this.writer = new Member("null@example.com", "null11223344!", Authority.NORMAL);
    }

    public void addComment(Comment comment) {
        // gif의 writer설정은 gif에서 함
        commentList.add(comment);
    }

    public void confirmWriter(Member writer) {
        this.writer = writer;
    }




}
