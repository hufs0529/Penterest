package penterest.spring.domain.gif.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Member;

import java.util.List;

@Data
@NoArgsConstructor
public class GifInfoByEmailDto {

    private String email;

    private List<Gif> gifList;

    public GifInfoByEmailDto(Member member) {
        this.email = member.getEmail();
        this.gifList = member.getGifList();
    }
}
