package penterest.spring.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.comment.dto.CommentSaveDto;
import penterest.spring.domain.comment.dto.CommentUpdateDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.comment.repository.CommentRepository;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.global.security.util.SecurityUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements  CommentService{

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final GifRepository gifRepository;

    @Override
    public void save(Long gifId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmWriter(memberRepository.findByEmail(SecurityUtil.getLoingUserEmail()));

        comment.confirmGif(gifRepository.findById(gifId).orElseThrow());

        commentRepository.save(comment);
    }

    @Override
    public void saveReComment(Long gifId, Long parentId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmWriter(memberRepository.findByEmail(SecurityUtil.getLoingUserEmail()));

        comment.confirmGif(gifRepository.findById(gifId).orElseThrow());

        commentRepository.save(comment);
    }

    @Override
    public void update(Long id, CommentUpdateDto commentUpdateDto) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(!comment.getWriter().getEmail().equals(SecurityUtil.getLoingUserEmail())){
            throw new Exception();
        }

        commentUpdateDto.content().ifPresent(comment::setContent);
    }

    @Override
    public void remove(Long id) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(!comment.getWriter().getEmail().equals(SecurityUtil.getLoingUserEmail())) {
            throw new Exception();
        }

        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        commentRepository.deleteAll(removableCommentList);
    }
}
