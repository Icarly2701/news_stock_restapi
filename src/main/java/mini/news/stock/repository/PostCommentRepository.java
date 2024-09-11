package mini.news.stock.repository;

import mini.news.stock.domain.post.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findByPostId(Long postId);
}
