package penterest.spring.domain.comment.dto;

import penterest.spring.domain.comment.entity.Comment;

public record CommentSaveDto (String content){

    public Comment toEntity() {
        return Comment.builder().content(content).build();
    }
}