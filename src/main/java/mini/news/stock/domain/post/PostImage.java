package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;

@Entity
@Getter
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private String postImagePath;
}
