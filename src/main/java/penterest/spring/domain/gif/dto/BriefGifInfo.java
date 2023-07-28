package penterest.spring.domain.gif.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import penterest.spring.domain.gif.entity.Gif;

@Data
@NoArgsConstructor
public class BriefGifInfo {

    private Long gifId;

    private String caption;
    private String url;
    private String writer;
    private String createdDate;

    public BriefGifInfo(Gif gif) {
        this.gifId = gif.getId();
        this.caption = gif.getCaption();
        this.url = gif.getUrl();
        this.writer = gif.getWriter().getEmail();
        this.createdDate = gif.getCreatedDate().toString();
    }
}
