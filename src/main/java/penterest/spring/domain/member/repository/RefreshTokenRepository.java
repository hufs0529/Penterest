package penterest.spring.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import penterest.spring.domain.member.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByKey(String key);
}
