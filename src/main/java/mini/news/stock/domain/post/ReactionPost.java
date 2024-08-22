package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.custom.Reaction;
import mini.news.stock.domain.news.News;
import mini.news.stock.domain.user.User;

import java.util.Date;

@Entity
@Getter
public class ReactionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reactionPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private Reaction reactionPostReaction;

    private Date reactionPostDate;
}
