package penterest.spring.domain.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import penterest.spring.domain.follow.entity.Follow;
import penterest.spring.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {

    Optional<Follow> findByToMemberAndFromMember(String toMember, String fromMember);

//    Long countByToAccount(Long memberId);
//    Long countByFromAccount(Long memberId);
    @Query(value = "select m from Follow f INNER JOIN Member m ON f.toMember = m.email where f.fromMember = :toMember")
    List<Follow> findAllByToMember(@Param("toMember") String toMember);


    @Query(value = "select m from Follow f INNER JOIN Member m ON f.fromMember = m.email where f.toMember = :fromMember")
    List<Follow> findAllByFromMember(@Param("fromMember") String fromMember);
}
