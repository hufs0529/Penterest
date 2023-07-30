package penterest.spring.domain.comment.dto;
import penterest.spring.domain.comment.entity.Comment;

import java.util.Optional;

public record CommentUpdateDto (Optional<String> content){ }