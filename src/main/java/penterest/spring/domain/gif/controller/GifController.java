package penterest.spring.domain.gif.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.gif.dto.BriefGifInfo;
import penterest.spring.domain.gif.dto.GifSaveDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;
import penterest.spring.domain.gif.repository.GifSearchQueryRepository;
import penterest.spring.domain.gif.service.GifService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gif")
@RequiredArgsConstructor
public class GifController {

    private final GifService gifService;
    private final GifSearchQueryRepository gifSearchQueryRepository;

    /**
     * 게시글 저장
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public void save(@Valid @RequestBody GifSaveDto gifSaveDto) throws Exception {
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

    // elasticsearch 적용시 writer에 입력시 gif 나올수 있도록
    @GetMapping("/getWriter/{gifId}")
    public ResponseEntity<String> getWriterEmailByGifId(@PathVariable("gifId") Long gifId){
        String writerEmail = gifService.findWriterEmailByGifId(gifId);
        return ResponseEntity.ok(writerEmail);
    }

    @PostMapping("/migrate")
    public ResponseEntity<String> migrateGifInfoDtosToElasticsearch() {
        gifService.migrateGifInfoDtosToElasticsearch();
        return ResponseEntity.ok("Migration completed.");
    }

    @GetMapping("/search/{caption}")
    public ResponseEntity<List<GifDocument>> searchByCaption(@PathVariable  String caption) {
        List<GifDocument> gifs = gifService.searchByCaption(caption);
        return ResponseEntity.ok(gifs);
    }
}