package penterest.spring.domain.gif.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import penterest.spring.domain.gif.entity.GifDocument;

import java.util.List;

public interface GIfSearchRepository extends ElasticsearchRepository<GifDocument, Long> {

    List<GifDocument> findByCaption(String caption);
}
