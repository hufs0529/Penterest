package penterest.spring.domain.gif.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import penterest.spring.domain.Like.dto.LikedGifDto;
import penterest.spring.domain.Like.repository.LikeRepository;
import penterest.spring.domain.comment.dto.CommentESDto;
import penterest.spring.domain.comment.repository.CommentRepository;
import penterest.spring.domain.gif.converter.GifInfoDtoToGifDocumentConverter;
import penterest.spring.domain.gif.dto.*;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;
import penterest.spring.domain.gif.repository.CustomGifRepository;
import penterest.spring.domain.gif.repository.GIfSearchRepository;
import penterest.spring.domain.gif.repository.GifRepository;
import penterest.spring.domain.gif.repository.GifSearchQueryRepository;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class GifServiceImpl implements  GifService{

    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final GifRepository gifRepository;
    private final MemberRepository memberRepository;
    private final GifSearchQueryRepository gifSearchQueryRepository;
    private final GIfSearchRepository gIfSearchRepository;
    private final GifInfoDtoToGifDocumentConverter converter;
    private final CustomGifRepository customGifRepository;


    @Override
    public Gif save(GifSaveDto gifSaveDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();

        Gif gif = gifSaveDto.toEntity();
        Member member = memberRepository.findByEmail(email);
        gif.confirmWriter(memberRepository, member);

        gifRepository.save(gif);
        return gif;
    }


    private boolean checkAuthority(Gif gif) {
        //String loginUserEmail = SecurityUtil.getLoginUserEmail();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();

        Member writer = gif.getWriter();
        return writer != null && writer.getEmail().equals(email);
    }


    @Override
    public String delete(Long id) throws Exception {
        Gif gif = gifRepository.findById(id).orElseThrow(()->
                new Exception());

        if (checkAuthority(gif) || gif.getWriter().getRole().equals("NORMAL")) {
            gifRepository.delete(gif);
        }else {
            throw new Exception("인가된 유저가 아닙니다");
        }
        return id + " id의 게시물이 지워졌습니다";
    }



    @Override
    public GifInfoDto getGifInfo(Long id) throws Exception {
        return new GifInfoDto(gifRepository.findGifById(id));
    }


    @Override
    public String findWriterEmailByGifId(Long gifId) {
        return gifRepository.findWriterEmailByGifId(gifId);
    }

    @Override
    public List<BriefGifInfo> findByCaption(String caption) {
        return null;
    }

    @Override
    public List<BriefGifInfo> findByMatchesCaption(String caption) {
        return gifSearchQueryRepository.findByMatchesCaption(caption)
                .stream()
                .map(BriefGifInfo::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<BriefGifInfo> findByContainsCaption(String caption) {
        return gifSearchQueryRepository.findByContainsCaption(caption)
                .stream()
                .map(BriefGifInfo::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentESDto> searchByComment(String content) {
        return gifSearchQueryRepository.searchCommentsByContent(content);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LikedGifDto> getLikeGifListWithEmail(String email) {
        List<LikedGifDto> likedGifDTOList = gifRepository.findLikedGifDetailsByEmail(email);
        return likedGifDTOList;
    }

    @Override
    public GifPagingDto getGifList(Pageable pageable, GifSearchCondition gifSearchCondition) {
        return new GifPagingDto(customGifRepository.search(gifSearchCondition, pageable));
    }

    @Override
    public void migrateGifInfoDtosToElasticsearch() {
        List<Gif> gifs = gifRepository.findAll();

        List<GifDocument> gifDocumentList = gifs.stream()
                .map(converter::convert)
                .collect(Collectors.toList());

        gIfSearchRepository.saveAll(gifDocumentList);
    }

    @Override
    public List<GifDocument> searchByCaption(String caption) {
        return gifSearchQueryRepository.findByMatchesCaption(caption);
    }

    @Override
    public List<Gif> getGifListByMemberEmail(String email) {

        return gifRepository.findGifListByMemberEmail(email);
    }
}
