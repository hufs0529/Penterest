package penterest.spring.domain.gif.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import penterest.spring.domain.comment.dto.CommentESDto;
import penterest.spring.domain.comment.entity.CommentDocument;
import penterest.spring.domain.comment.repository.CommentRepository;
import penterest.spring.domain.gif.dto.SearchCondition;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class GifSearchQueryRepository {

    private final ElasticsearchOperations operations;
    private final GifRepository gifRepository;
    private final CommentRepository commentRepository;

    public List<GifDocument> findByCondition(SearchCondition searchCondition) {
        CriteriaQuery query = createConditionCriteriaQuery(searchCondition);

        SearchHits<GifDocument> searchHits = operations.search(query, GifDocument.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }


    private CriteriaQuery createConditionCriteriaQuery(SearchCondition searchCondition) {
        CriteriaQuery query = new CriteriaQuery(new Criteria());

        if (searchCondition == null)
            return query;
        if (searchCondition.getCaption() != null)
            query.addCriteria(Criteria.where("caption").is(searchCondition.getCaption()));

        return query;
    }

    public List<GifDocument> findByMatchesCaption(String caption) {
        Criteria criteria = Criteria.where("caption").matches(caption);
        Query query = new CriteriaQuery(criteria);
        SearchHits<GifDocument> searchHits = operations.search(query, GifDocument.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public List<CommentESDto> searchCommentsByContent(String content) {
        Criteria criteria = Criteria.where("content").matches(content);
        Query query = new CriteriaQuery(criteria);
        SearchHits<CommentDocument> searchHits = operations.search(query, CommentDocument.class);
        return searchHits.stream()
                .map(hit -> {
                    CommentESDto dto = CommentESDto.fromCommentDocument(hit.getContent());

                    Optional<Gif> gif = gifRepository.findById(dto.getGif().getId());
                    if (gif.isPresent()) {
                        Gif gifValue = gif.get();
                        dto.getGif().setId(gifValue.getId());
                        dto.getGif().setUrl(gifValue.getUrl());
                        dto.getGif().setCaption(gifValue.getCaption());
                    } else {
                        // 원하는 처리를 하거나 예외를 던지는 등의 방식으로 값이 없을 때 처리합니다.
                        // 예: dto.getGif().setId(null);
                        //     dto.getGif().setUrl("No URL available");
                        //     dto.getGif().setCaption("No caption available");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

//    public List<Comment> findCommentsByContentContaining(String content) {
//        Criteria criteria = Criteria.where("caption").matches(content);
//        Query query = new CriteriaQuery(criteria);
//
//        SearchHits<GifDocument> searchHits = operations.search(query, GifDocument.class);
//
//        return searchHits.stream()
//                .map(SearchHit::getContent)
//                .map(this::mapToComment) // Assuming you have a method to map from CommentDocument to Comment
//                .collect(Collectors.toList());
//    }
//
//    private Comment mapToComment(CommentDocument commentDocument) {
//        Comment comment = new Comment();
//        comment.setId(commentDocument.getId());
//        comment.setContent(commentDocument.getContent());
//        // Map other fields as needed
//        return comment;
//    }





    public List<GifDocument> findByContainsCaption(String caption) {
        Criteria criteria = Criteria.where("caption").matches(caption);
        Query query = new CriteriaQuery(criteria);
        SearchHits<GifDocument> searchHits = operations.search(query, GifDocument.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
