package penterest.spring.domain.gif.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import penterest.spring.domain.gif.dto.SearchCondition;
import penterest.spring.domain.gif.entity.GifDocument;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class GifSearchQueryRepository {

    private final ElasticsearchOperations operations;

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

    public List<GifDocument> findByContainsCaption(String caption) {
        Criteria criteria = Criteria.where("caption").matches(caption);
        Query query = new CriteriaQuery(criteria);
        SearchHits<GifDocument> searchHits = operations.search(query, GifDocument.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
