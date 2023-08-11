package penterest.spring.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import penterest.spring.domain.comment.entity.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT m.commentList FROM Member m WHERE m.email = :email")
    List<Comment> findCommentListByMemberEmail(@Param("email") String email);


}