package penterest.spring.domain.comment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.comment.dto.CommentSaveDto;
import penterest.spring.domain.comment.dto.CommentUpdateDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.comment.service.CommentService;
import penterest.spring.domain.gif.service.GifService;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final GifService gifService;

    @PostMapping("/save/{gifId}")
    public ResponseEntity<String> commentSave(@PathVariable("gifId") Long gifId,
                                              @RequestBody CommentSaveDto commentSaveDto) {
        try {
            commentService.save(gifId, commentSaveDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentSaveDto + " 댓글 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the comment.");
        }
    }


    @PostMapping("/save/{gifId}/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> reCommentSave(@PathVariable("gifId") Long gifId,
                                                @PathVariable("commentId") Long commentId,
                                                @RequestBody CommentSaveDto commentSaveDto) {
        try {
            commentService.saveReComment(gifId, commentId, commentSaveDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentSaveDto + " 댓글 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the comment.");
        }
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<String> update(@PathVariable("commentId") Long commentId,
                                         @RequestBody CommentUpdateDto commentUpdateDto) {
        try {
            commentService.update(commentId, commentUpdateDto);
            return ResponseEntity.ok("Comment updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the comment.");
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> delete(@PathVariable("commentId") Long commentId) {
        try {
            commentService.remove(commentId);
            return ResponseEntity.ok("Comment deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the comment.");
        }
    }

    @GetMapping("/commentListByWriter/{email}")
    public ResponseEntity<List<Comment>> getCommentByWriterEmail(@PathVariable String email) {
        List<Comment> commentList = commentService.findCommentListByMemberEmail(email);
        return ResponseEntity.ok(commentList);
    }
}
