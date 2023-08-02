package penterest.spring.domain.follow.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import penterest.spring.domain.member.entity.Member;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
@Table(name="follow")
@IdClass(Follow.PK.class)
public class Follow {


    @Id
    @Column(name = "toMember", insertable = false, updatable = false)
    private String toMember;

    @Id
    @Column(name = "fromMember", insertable = false, updatable = false)
    private String fromMember;

    public Follow(String toMember, String fromMember){
        this.toMember = toMember;
        this.fromMember = fromMember;
    }

    public static class PK implements Serializable {
        String toMember;
        String fromMember;
    }

}
