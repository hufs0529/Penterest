package penterest.spring.domain.gif.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.gif.dto.GifSaveDto;
import penterest.spring.domain.gif.service.GifService;

import javax.validation.Valid;

@RestController
@RequestMapping("/gif")
@RequiredArgsConstructor
public class GifController {

    private final GifService gifService;


    /**
     * 게시글 저장
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public void save(@Valid @ModelAttribute GifSaveDto gifSaveDto) throws Exception {
        gifService.save(gifSaveDto);
    }


    /**
     * 게시글 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{gifId}")
    public void delete(@PathVariable("gifId") Long gifId) throws Exception {
        gifService.delete(gifId);
    }


    /**
     * 게시글 조회
     */
    @GetMapping("/get/{gifId}")
    public ResponseEntity getInfo(@PathVariable("gifId") Long gifId) throws Exception {
        return ResponseEntity.ok(gifService.getGifInfo(gifId));
    }


}