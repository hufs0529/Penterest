package penterest.spring.domain.gif.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import penterest.spring.domain.Like.dto.LikedGifDto;
import penterest.spring.domain.comment.dto.CommentESDto;
import penterest.spring.domain.gif.dto.GifData;
import penterest.spring.domain.gif.dto.GifSaveDto;
import penterest.spring.domain.gif.dto.GifSearchCondition;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;
import penterest.spring.domain.gif.service.GifService;
import penterest.spring.domain.member.controller.MemberController;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gif")
@RequiredArgsConstructor
public class GifController {

    private final GifService gifService;

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private final Logger logger2 = LoggerFactory.getLogger(GifController.class.getName());

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

    // RestTemplate 나중에 구현해보자

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/save")
//    public ResponseEntity save(@Valid @RequestBody Map<String, String> data) throws Exception {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders header = new HttpHeaders();
//        String flask_url = "http://localhost:5000/upload";
//
//        header.setContentType(MediaType.APPLICATION_JSON);
//        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        //HttpEntity<Map<String, String>> entity = new HttpEntity<>(jsonData, header);
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(data, header);
//
//        ResponseEntity<String> response = restTemplate.exchange(flask_url, HttpMethod.POST, entity, String.class);
//        logger2.info(response.toString());
//        logger2.info(String.valueOf(response.getStatusCode()));
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            try {
//                // JSON 파싱
//                String responseBody = response.getBody();
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode jsonNode = objectMapper.readTree(responseBody);
//
//                String urlFromResponse = jsonNode.get("url").asText();
//                String captionFromResponse = jsonNode.get("caption").asText();
//
//                logger2.info(urlFromResponse.toString());
//                GifSaveDto gifSaveDto = new GifSaveDto(urlFromResponse, captionFromResponse);
//
//                Gif gif = gifService.save(gifSaveDto);
//                return ResponseEntity
//                        .status(HttpStatus.CREATED)
//                        .body("Gif created with ID: " + gif.getId());
//            }catch (MethodArgumentNotValidException ex){
//                logger.warn("Validation failed: {}", ex.getMessage());
//                throw ex;
//            }
//        }else {
//            // Handle other response statuses here
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error occurred during communication with Flask");
//        }
//    }


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

    /**
     * 조건에 맞는 게시글 리스트
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public ResponseEntity search(
            @RequestParam(value = "sort", defaultValue = "createDate") String sort,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @ModelAttribute GifSearchCondition gifSearchCondition) {
        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(sort).ascending());
        return ResponseEntity.ok(gifService.getGifList(pageable, gifSearchCondition));
    }

    // gif id 입력시 작성자 노출
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

    // email 입력시 email이 작성한 gif list반환
    @GetMapping("/gifListByWriter/{email}")
    public ResponseEntity<List<Gif>> getGifByWriterEmail(@PathVariable String email) {
        List<Gif> gifList = gifService.getGifListByMemberEmail(email);
        return ResponseEntity.ok(gifList);
    }

    // es에 gif 설명 입력시 해당 gif list반환
    @GetMapping("/searchByCaption/{caption}")
    public ResponseEntity<List<GifDocument>> searchByCaption(@PathVariable String caption) {
        List<GifDocument> gifs = gifService.searchByCaption(caption);
        return ResponseEntity.ok(gifs);
    }

    // es에 gif 댓글 입력히 해당 gif list 반환
    @GetMapping("/searchByComment/{comment}")
    public ResponseEntity<List<CommentESDto>> searchByComment(@PathVariable String comment) {
        List<CommentESDto> commentDocumentList = gifService.searchByComment(comment);
        return ResponseEntity.ok(commentDocumentList);
    }
}