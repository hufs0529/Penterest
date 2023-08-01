package penterest.spring.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;
import penterest.spring.domain.comment.entity.Comment;


public record CommentSaveDto (String content){

    public Comment toEntity() {
        return Comment.builder().content(content).build();
    }

}