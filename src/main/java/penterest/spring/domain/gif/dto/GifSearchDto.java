package penterest.spring.domain.gif.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import penterest.spring.domain.gif.entity.Gif;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GifSearchDto {

    private List<BriefGifInfo> gifList = new ArrayList<>();

    public GifSearchDto(Page<Gif> searchResults) {
        this.gifList = searchResults.getContent().stream().map(BriefGifInfo::new).toList();
    }
}
