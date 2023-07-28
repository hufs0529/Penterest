package penterest.spring.domain.gif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import penterest.spring.domain.gif.controller.GIfController;

public interface PostRepository extends JpaRepository<GIfController, Long> {

}
