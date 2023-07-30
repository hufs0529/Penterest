package penterest.spring.domain.comment.service;

import penterest.spring.domain.comment.dto.CommentSaveDto;
import penterest.spring.domain.comment.dto.CommentUpdateDto;

public interface CommentService {

    void save(Long postId, CommentSaveDto commentSaveDto);

    void saveReComment(Long postId, Long parentId, CommentSaveDto commentSaveDto);

    void update(Long id, CommentUpdateDto commentUpdateDto) throws Exception;

    void remove(Long id) throws Exception;
}
