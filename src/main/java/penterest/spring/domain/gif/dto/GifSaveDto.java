package penterest.spring.domain.gif.dto;

import penterest.spring.domain.gif.entity.Gif;

public record GifSaveDto(String url, String caption) {

    public Gif toEntity() {

        return Gif.builder().url(url).caption(caption).build();
    }
}
