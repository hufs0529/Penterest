package penterest.spring.chat.entity;

import lombok.*;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.global.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Member receiver;

}
