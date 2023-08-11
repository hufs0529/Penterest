package penterest.spring.domain.gif.service;

import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.dto.BriefGifInfo;
import penterest.spring.domain.gif.dto.GifInfoByEmailDto;
import penterest.spring.domain.gif.dto.GifInfoDto;
import penterest.spring.domain.gif.dto.GifSaveDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;

import java.util.List;


public interface GifService {

    void save(GifSaveDto gifSaveDto) throws Exception;

    void delete(Long id) throws Exception;

    GifInfoDto getGifInfo(Long id) throws Exception;


    String findWriterEmailByGifId(Long gifId);

    List<BriefGifInfo> findByCaption(String caption);

    List<BriefGifInfo> findByMatchesCaption(String caption);

    List<BriefGifInfo> findByContainsCaption(String caption);

    void migrateGifInfoDtosToElasticsearch();

    List<GifDocument> searchByCaption(String caption);

    List<Gif> getGifListByMemberEmail(String email);

}
