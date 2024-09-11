package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;

import java.util.Date;

@Entity
@Getter
public class RecentPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recentPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private Date recentPostDate;

    public static RecentPost addRecentPost(User user, Post post){
        RecentPost recentPost = new RecentPost();
        recentPost.post = post;
        recentPost.user = user;
        return recentPost;
    }

}
