package penterest.spring.domain.gif.controller;

import co.elastic.clients.elasticsearch.nodes.Http;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.Like.dto.LikedGifDto;
import penterest.spring.domain.comment.dto.CommentESDto;
import penterest.spring.domain.gif.dto.GifSaveDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;
import penterest.spring.domain.gif.repository.GifSearchQueryRepository;
import penterest.spring.domain.gif.service.GifService;
import penterest.spring.domain.member.controller.MemberController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gif")
@RequiredArgsConstructor
public class GifController {

    private final GifService gifService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    /**
     * 게시글 저장
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody GifSaveDto gifSaveDto) throws Exception {
        try {
            Gif gif = gifService.save(gifSaveDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Gif created with ID: " + gif.getId());
        }catch (MethodArgumentNotValidException ex){
            logger.warn("Validation failed: {}", ex.getMessage());
            throw ex;
        }
    }


    /**
     * 게시글 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{gifId}")
    public ResponseEntity delete(@PathVariable("gifId") Long gifId) throws Exception {
        return ResponseEntity.ok(gifService.delete(gifId));
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

    //좋아요한 gif 조회
    @GetMapping("/getWithLike/{email}")
    public ResponseEntity<List<LikedGifDto>> getLikeGifListWithLike(@PathVariable("email") String email) {
        List<LikedGifDto> gifList = gifService.getLikeGifListWithEmail(email);
        return ResponseEntity.ok(gifList);
    }

    @PostMapping("/migrate")
    public ResponseEntity<String> migrateGifInfoDtosToElasticsearch() {
        gifService.migrateGifInfoDtosToElasticsearch();
        return ResponseEntity.ok("Migration completed.");
    }

    @GetMapping("/gifListByWriter/{email}")
    public ResponseEntity<List<Gif>> getGifByWriterEmail(@PathVariable String email) {
        List<Gif> gifList = gifService.getGifListByMemberEmail(email);
        return ResponseEntity.ok(gifList);
    }

    @GetMapping("/searchByCaption/{caption}")
    public ResponseEntity<List<GifDocument>> searchByCaption(@PathVariable String caption) {
        List<GifDocument> gifs = gifService.searchByCaption(caption);
        return ResponseEntity.ok(gifs);
    }

    @GetMapping("/searchByComment/{comment}")
    public ResponseEntity<List<CommentESDto>> searchByComment(@PathVariable String comment) {
        List<CommentESDto> commentDocumentList = gifService.searchByComment(comment);
        return ResponseEntity.ok(commentDocumentList);
    }
}