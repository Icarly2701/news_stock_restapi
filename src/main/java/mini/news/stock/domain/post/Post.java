package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;

import java.util.Date;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Date postDate;

    private int postRecommendCount;

    private int postViewCount;

    private String postTitle;

    private String postContent;
}
