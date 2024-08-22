package mini.news.stock.domain.news;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    private String newsHeadline;

    private String newsUrl;

    private Date newsDate;

    private int newsViewCount;

    private int newsRecommendCount;

    private String newsContent;
}
