package mini.news.stock.repository;

import mini.news.stock.domain.post.RecentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentPostRepository extends JpaRepository<RecentPost, Long> {
}
