package penterest.spring.domain.Like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import penterest.spring.domain.Like.entity.Like;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByMemberAndGif(Member member, Gif gif);

//    @Query("SELECT g.id AS gifId, g.caption AS gifCaption, g.url AS gifUrl " +
//            "FROM Like l " +
//            "JOIN l.gif g " +
//            "JOIN l.member m " +
//            "WHERE m.email = :email")
//    List<LikedGifDo> findLikedGifDetailsByEmail(@Param("email") String email);

}