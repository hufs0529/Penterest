package penterest.spring.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penterest.spring.domain.comment.dto.CommentSaveDto;
import penterest.spring.domain.comment.dto.CommentUpdateDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.comment.repository.CommentRepository;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.domain.member.service.MemberServiceImpl;
import penterest.spring.global.security.util.SecurityUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements  CommentService{

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final GifRepository gifRepository;
    private final MemberServiceImpl memberServiceImpl;


    public void save(Long gifId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();
        String loggedInUserEmail = SecurityUtil.getLoginUserEmail();
        Member writer = memberRepository.findByEmail(loggedInUserEmail);

        if (writer == null) {
            Member defaultWriter = memberRepository.findByEmail("user@example.com");
            writer = defaultWriter;
        }

        Gif gif = gifRepository.findById(gifId).orElseThrow();

        comment.confirmWriter(memberRepository, writer);
        comment.confirmGif(gif);
        commentRepository.save(comment);
    }



    @Override
    public void saveReComment(Long gifId, Long parentId, CommentSaveDto commentSaveDto) {
        String loggedInUserEmail = SecurityUtil.getLoginUserEmail();
        Member writer = memberRepository.findByEmail(loggedInUserEmail);

        if (writer == null) {
            Member defaultMember = memberRepository.findByEmail("user@example.com");
            writer = defaultMember;
        }

        Gif gif = gifRepository.findById(gifId).orElseThrow();
        Comment parent = commentRepository.findById(parentId).orElseThrow();

        Comment comment = commentSaveDto.toEntity();
        comment.confirmWriter(memberRepository, writer);
        comment.confirmGif(gif);
        comment.confirmParent(parent);
        commentRepository.save(comment);
    }

    @Override
    public void update(Long id, CommentUpdateDto commentUpdateDto) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(!comment.getWriter().getEmail().equals(SecurityUtil.getLoginUserEmail())
            || comment.getWriter().getAuthorities().equals("NORMAL")){
            throw new Exception();
        }

        commentUpdateDto.content().ifPresent(comment::setContent);
    }

    @Override
    public void remove(Long id) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(!comment.getWriter().getEmail().equals(SecurityUtil.getLoginUserEmail())
            || comment.getWriter().getAuthorities().equals("NORMAL")) {
            throw new Exception();
        }

        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        commentRepository.deleteAll(removableCommentList);
    }

}
