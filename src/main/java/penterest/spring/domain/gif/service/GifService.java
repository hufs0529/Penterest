package penterest.spring.domain.gif.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import penterest.spring.domain.Like.dto.LikedGifDto;
import penterest.spring.domain.comment.dto.CommentESDto;
import penterest.spring.domain.gif.dto.*;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;

import java.util.List;


public interface GifService {

    Gif save(GifSaveDto gifSaveDto) throws Exception;

    String delete(Long id) throws Exception;

    GifInfoDto getGifInfo(Long id) throws Exception;


    String findWriterEmailByGifId(Long gifId);

    List<BriefGifInfo> findByCaption(String caption);

    List<BriefGifInfo> findByMatchesCaption(String caption);

    List<BriefGifInfo> findByContainsCaption(String caption);

    void migrateGifInfoDtosToElasticsearch();

    List<GifDocument> searchByCaption(String caption);

    List<Gif> getGifListByMemberEmail(String email);

    List<CommentESDto> searchByComment(String content);

    List<LikedGifDto> getLikeGifListWithEmail(String email);

    GifPagingDto getGifList(Pageable pageable, GifSearchCondition gifSearchCondition);
}
