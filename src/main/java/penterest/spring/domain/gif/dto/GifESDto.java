package penterest.spring.domain.gif.dto;

import lombok.Getter;
import lombok.Setter;
import penterest.spring.domain.gif.entity.Gif;

@Getter
@Setter
public class GifESDto {

    private Long id;
    private String url;
    private String caption;

    public static GifESDto fromGif(Gif gif) {
        GifESDto dto = new GifESDto();
        dto.setId(gif.getId());
        dto.setUrl(gif.getUrl());
        dto.setCaption(gif.getCaption());
        return dto;
    }
}
