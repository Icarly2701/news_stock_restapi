package mini.news.stock.repository;

import mini.news.stock.domain.post.TempPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TempPostRepository extends JpaRepository<TempPost, Long> {
    Optional<TempPost> findByUserUsername(String username);
}
