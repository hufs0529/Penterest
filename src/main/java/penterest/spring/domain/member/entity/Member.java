package penterest.spring.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.global.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = Authority.NORMAL;
    }

    @OneToMany(mappedBy = "writer") //== 회원탈퇴 -> 작성한 게시물, 댓글 모두 삭제 ==//cascade = ALL, orphanRemoval = true
    private List<Gif> gifList = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Comment> commentList = new ArrayList<>();

    public void addGif(Gif gif) {
        // gif의 writer설정은 gif에서 함
        gifList.add(gif);
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    public void addUserAuthority() {
        this.authority = Authority.NORMAL;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    public void setPassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }


}
