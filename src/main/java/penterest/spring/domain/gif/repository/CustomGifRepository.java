package penterest.spring.domain.gif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import penterest.spring.domain.gif.dto.GifSearchCondition;
import penterest.spring.domain.gif.entity.Gif;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomGifRepository {

    @PersistenceContext
    private EntityManager em;

    private long getTotalCount(GifSearchCondition gifSearchCondition) {
        String jpql = "select count(g) from Gif g where 1=1";
        if (gifSearchCondition.getCaption() != null) {
            jpql += " and g.caption like :caption";
        }


        TypedQuery<Long> countQuery = em.createQuery(jpql, Long.class);

        if (gifSearchCondition.getCaption() != null) {
            countQuery.setParameter("caption", "%" + gifSearchCondition.getCaption() + "%");
        }
        return countQuery.getSingleResult();
    }

    public Page<Gif> search(GifSearchCondition gifSearchCondition, Pageable pageable) {
        String jpql = "select g from Gif g where 1=1";
        if (gifSearchCondition.getCaption() != null) {
            jpql += " and g.caption like :caption";
        }

        // Create query
        TypedQuery<Gif> query = em.createQuery(jpql, Gif.class);

        // Set parameters
        if (gifSearchCondition.getCaption() != null) {
            query.setParameter("caption", "%" + gifSearchCondition.getCaption() + "%");
        }

        // Pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Fetch results and total count
        List<Gif> content = query.getResultList();
        long total = getTotalCount(gifSearchCondition);

        return new PageImpl<>(content, pageable, total);
    }
}
