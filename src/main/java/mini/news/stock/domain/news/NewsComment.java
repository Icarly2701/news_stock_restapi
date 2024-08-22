package mini.news.stock.domain.news;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;

import java.util.Date;

@Entity
@Getter
public class NewsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private NewsComment parentNewsComment;

    private Date newsCommentDate;

    private String newsCommentContent;

    private int newsCommentRecommendCount;

    private int newsCommentDepth;

    private int newsCommentGroup;
}
