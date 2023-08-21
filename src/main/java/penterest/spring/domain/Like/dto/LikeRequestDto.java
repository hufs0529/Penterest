package penterest.spring.domain.Like.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeRequestDto {

    private Long memberId;
    private Long gifId;

    public LikeRequestDto(Long memberId, Long gifId){
        this.memberId = memberId;
        this.gifId = gifId;
    }
}
