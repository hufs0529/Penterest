package penterest.spring.domain.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.global.domain.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gif_id")
    @JsonBackReference
    private Gif gif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Comment parent;


    @Lob
    private String content;

    private boolean isRemoved = false;

    // 부모 댓글 삭제해도 자식 댓그 존재
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Comment> childList = new ArrayList<>();


    public void confirmWriter(MemberRepository memberRepository, Member writer) {
        Member defaultMember = memberRepository.findByEmail("user@example.com");
        this.writer = writer != null ? writer : defaultMember;
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


    public List<Comment> findRemovableList() {

        List<Comment> result = new ArrayList<>();

        Optional.ofNullable(this.parent).ifPresentOrElse(

                parentComment -> {
                    if(parentComment.isRemoved() && parentComment.isAllChildRemoved()) {
                        result.addAll((parentComment.getChildList()));
                        result.add(parentComment);
                    }
                },

                () -> {
                    if (isAllChildRemoved()) {
                        result.add(this);
                        result.addAll(this.getChildList());
                    }
                }
        );
        return result;
    }

    private boolean isAllChildRemoved() {

        return getChildList().stream()
                .map(Comment::isRemoved)
                .findAny()
                .orElse(true);
    }
}
