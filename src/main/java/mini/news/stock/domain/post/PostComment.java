package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.news.NewsComment;
import mini.news.stock.domain.user.User;

import java.util.Date;

@Entity
@Getter
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private PostComment parentPostComment;

    private Date postCommentDate;

    private String postCommentContent;

    private int postCommentRecommendCount;

    private int postCommentDepth;

    private int postCommentGroup;
}
