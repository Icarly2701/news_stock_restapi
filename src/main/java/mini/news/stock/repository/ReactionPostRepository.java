package mini.news.stock.repository;

import mini.news.stock.domain.post.ReactionPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactionPostRepository extends JpaRepository<ReactionPost, Long> {
    Optional<ReactionPost> findByUserId(Long userId);
}
