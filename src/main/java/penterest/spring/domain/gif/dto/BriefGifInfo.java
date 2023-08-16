package penterest.spring.domain.gif.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BriefGifInfo {

    private Long gifId;
    private String caption;
    private String url;
    private String writer;
    private List<Comment> commentList;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BriefGifInfo(Gif gif) {
        this.gifId = gif.getId();
        this.caption = gif.getCaption();
        this.url = gif.getUrl();
        this.writer = gif.getWriter().getEmail();
        this.commentList = gif.getWriter().getCommentList();
        this.createdDate = gif.getCreatedDate();
    }

    // Conversion method from GifDocument to BriefGifInfo
    public static BriefGifInfo from(GifDocument gifDocument) {
        BriefGifInfo briefGifInfo = new BriefGifInfo();
        briefGifInfo.setGifId(gifDocument.getGif_id());
        briefGifInfo.setCaption(gifDocument.getCaption());
        briefGifInfo.setUrl(gifDocument.getUrl());
        briefGifInfo.setCreatedDate(gifDocument.getCreatedDate());
        briefGifInfo.setModifiedDate(gifDocument.getModifiedDate());
        return briefGifInfo;
    }
}
