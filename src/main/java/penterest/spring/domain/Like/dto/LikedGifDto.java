package penterest.spring.domain.Like.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LikedGifDto {
    private Long gifId;
    private String gifCaption;
    private String gifUrl;
}
