package penterest.spring.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.member.entity.Member;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberGifDto {

    private Long id;
    private String url;
    private String caption;

}