package penterest.spring.domain.comment.service;

import org.springframework.data.jpa.repository.Query;
import penterest.spring.domain.comment.dto.CommentSaveDto;
import penterest.spring.domain.comment.dto.CommentUpdateDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;

import java.util.List;

public interface CommentService {

    void save(Long postId, CommentSaveDto commentSaveDto);

    //void save(Long postId, CommentSaveDto commentSaveDto);

    void saveReComment(Long postId, Long parentId, CommentSaveDto commentSaveDto);

    void update(Long id, CommentUpdateDto commentUpdateDto) throws Exception;

    void remove(Long id) throws Exception;

    @Query("SELECT m.commentList FROM Member m WHERE m.email = :email")
    List<Comment> findCommentListByMemberEmail(String email);
}
