package penterest.spring.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.global.domain.BaseTimeEntity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Table(name = "member")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "user_authority",
//            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
//    private Set<Authority> authorities;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Role role;

    @OneToMany(mappedBy = "writer")
    @JsonManagedReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "writer") //== 회원탈퇴 -> 작성한 게시물, 댓글 모두 삭제 ==//cascade = ALL, orphanRemoval = true
    @JsonManagedReference
    private List<Gif> gifList = new ArrayList<>();

    @Builder
    public Member(String email, String password, Set<Authority> authorities) {
        this.email = email;
        this.password = password;
    }

    private Set<Authority> getDefaultAuthorities() {
        Set<Authority> defaultAuthorities = new HashSet<>();
        defaultAuthorities.add(new Authority("NORMAL"));
        return defaultAuthorities;
    }

    public void addGif(Gif gif) {
        // gif의 writer설정은 gif에서 함
        gifList.add(gif);
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
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

    public void addUserAuthority() {
        this.role = Role.USER;
    }


}
