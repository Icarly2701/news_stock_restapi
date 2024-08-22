package mini.news.stock.domain.news;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;

import java.util.Date;

@Entity
@Getter
public class RecentNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recentNewsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    private Date recentNewsDate;
}
