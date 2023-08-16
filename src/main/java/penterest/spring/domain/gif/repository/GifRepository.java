package penterest.spring.domain.gif.repository;

import antlr.collections.impl.LList;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import penterest.spring.domain.gif.dto.GifInfoByEmailDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface GifRepository extends JpaRepository<Gif, Long> {

    //Gif 엔티티와 연관된 writer 속성에 대한 쿼리를 수행할 때, @EntityGraph를 사용하여 한 번의 쿼리로 Gif와 연관된 writer 엔티티를 함께 로딩할 수 있다
    @EntityGraph(attributePaths = {"writer"})
    Optional<Gif> findWriterById(Long id);

    @Query("SELECT g.writer.email FROM Gif g WHERE g.id = :gifId")
    String findWriterEmailByGifId(Long gifId);

    // member의 email을 이용하여 해당 member의 gifList 조회
    @Query("SELECT m.gifList FROM Member m WHERE m.email = :email")
    List<Gif> findGifListByMemberEmail(String email);

    //Gif findById(Long id);
}
