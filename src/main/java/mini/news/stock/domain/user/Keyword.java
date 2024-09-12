package mini.news.stock.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String keywordContent;

    public static Keyword addKeyword(User user, String keywordContent){
        Keyword keyword = new Keyword();
        keyword.user = user;
        keyword.keywordContent = keywordContent;
        return keyword;
    }
}
