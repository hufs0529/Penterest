package penterest.spring.domain.gif.dto;

import lombok.Getter;
import lombok.Setter;
import penterest.spring.domain.gif.entity.Gif;

import java.util.Optional;

@Getter
@Setter
public class GitESDto {

    private Long id;
    private String url;
    private String caption;

    public static GitESDto fromGif(Gif gif) {
        GitESDto dto = new GitESDto();
        dto.setId(gif.getId());
        dto.setUrl(gif.getUrl());
        dto.setCaption(gif.getCaption());
        return dto;
    }
}
