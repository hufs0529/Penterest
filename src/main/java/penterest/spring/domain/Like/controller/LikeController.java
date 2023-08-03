package penterest.spring.domain.Like.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import penterest.spring.domain.Like.dto.LikeRequestDto;
import penterest.spring.domain.Like.service.LikeService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/up")
    public ResponseEntity<LikeRequestDto> like (@RequestBody @Valid LikeRequestDto likeRequestDto) throws Exception {
        likeService.like(likeRequestDto);
        return ResponseEntity.ok(likeRequestDto);
    }

    @PostMapping("/down")
    public ResponseEntity<LikeRequestDto> disLike(@RequestBody @Valid LikeRequestDto likeRequestDto) throws Exception {
        likeService.dislike(likeRequestDto);
        return ResponseEntity.ok(likeRequestDto);
    }

}
