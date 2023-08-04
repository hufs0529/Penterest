package penterest.spring.domain.gif.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;

import java.util.List;

@Data
@NoArgsConstructor
public class BriefGifInfo {

    private Long gifId;
    private String caption;
    private String url;
    private String writer;
    private List<Comment> commentList;
    private String createdDate;

    public BriefGifInfo(Gif gif) {
        this.gifId = gif.getId();
        this.caption = gif.getCaption();
        this.url = gif.getUrl();
        this.writer = gif.getWriter().getEmail();
        this.commentList = gif.getWriter().getCommentList();
        this.createdDate = gif.getCreatedDate().toString();
    }

    // Conversion method from GifDocument to BriefGifInfo
    public static BriefGifInfo from(GifDocument gifDocument) {
        BriefGifInfo briefGifInfo = new BriefGifInfo();
        briefGifInfo.setGifId(gifDocument.getId());
        briefGifInfo.setCaption(gifDocument.getCaption());
        briefGifInfo.setUrl(gifDocument.getUrl());
        // Set other fields as needed.
        return briefGifInfo;
    }
}
