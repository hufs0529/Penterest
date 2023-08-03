package penterest.spring.domain.Like.entity;
import lombok.*;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "like_info")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gif_id", updatable = false)
    private Gif gif;

    @Builder
    public Like(Member member, Gif gif) {
        this.member = member;
        this.gif = gif;
    }


}
