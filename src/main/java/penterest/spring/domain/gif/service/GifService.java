package penterest.spring.domain.gif.service;

import penterest.spring.domain.gif.dto.GifInfoDto;
import penterest.spring.domain.gif.dto.GifSaveDto;


public interface GifService {

    void save(GifSaveDto gifSaveDto) throws Exception;

    void delete(Long id) throws Exception;

    GifInfoDto getGifInfo(Long id) throws Exception;

}
