package penterest.spring.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import penterest.spring.domain.comment.entity.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long>{

}