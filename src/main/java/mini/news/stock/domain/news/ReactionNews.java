package mini.news.stock.domain.news;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.custom.Reaction;
import mini.news.stock.domain.user.User;

import java.util.Date;

@Entity
@Getter
public class ReactionNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reactionNewsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    private Reaction reactionNewsReaction;

    private Date reactionNewsDate;
}
