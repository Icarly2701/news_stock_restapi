package mini.news.stock.domain.news;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    private String newsHeadline;

    @Column(unique = true)
    private String newsUrl;

    private Date newsDate;

    private int newsViewCount;

    private int newsRecommendCount;

    private String newsContent;

    public static News makeNewsEntity(NewsItem newsItem){
        News news = new News();
        news.newsContent = newsItem.getDescription();
        news.newsDate = newsItem.getPubDate();
        news.newsHeadline = news.getNewsHeadline();;
        news.newsRecommendCount = 0;
        news.newsViewCount = 0;
        news.newsUrl = news.getNewsUrl() != null ? newsItem.getLink()
                : newsItem.getOriginallink();

        return news;
    }

    public void newsCheck(){
        newsRecommendCount++;
    }
}
