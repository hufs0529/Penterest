package penterest.spring.domain.Like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import penterest.spring.domain.Like.entity.Like;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Member;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByMemberAndGif(Member member, Gif gif);
}