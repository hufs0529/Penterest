package penterest.spring.domain.gif.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import penterest.spring.domain.gif.entity.Gif;

import java.util.List;

@Getter
@AllArgsConstructor
public class GifPagingDto {

    private List<GifInfoDto> postList;
    private int totalPageCount;
    private int curPageNum;
    private long totalPostCount;
    private int currentPagePostCount;

    public GifPagingDto(Page<Gif> searchResult){
        this.totalPageCount = searchResult.getTotalPages();
        this.curPageNum = searchResult.getNumber();
        this.totalPostCount = searchResult.getTotalElements();
        this.currentPagePostCount = searchResult.getNumberOfElements();
        this.postList = searchResult.getContent().stream().map(GifInfoDto::new).toList();
    }
}