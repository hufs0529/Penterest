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
    @Query(value = "select f from Follow f where f.fromMember = :toMember")
    List<Follow> findByFromMember(@Param("toMember") String toMember);

    @Query(value = "select f from Follow f where f.toMember = :fromMember")
    List<Follow> findByToMember(@Param("fromMember") String fromMember);
}
