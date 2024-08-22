package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;

@Entity
@Getter
public class TempPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tempPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String tempPostTitle;

    private String tempPostContent;
}
