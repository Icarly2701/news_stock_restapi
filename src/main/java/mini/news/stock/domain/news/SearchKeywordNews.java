package mini.news.stock.domain.news;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.Date;

@Getter
@RedisHash(value = "SearchKeywordNews", timeToLive = 1800)
public class SearchKeywordNews {

    @Id
    private String newsId;

    private String newsHeadLine;
    private String newsUrl;
    private Date newsDate;
    private String newsContent;

    public SearchKeywordNews(String newsHeadLine, String newsUrl, Date newsDate, String newsContent) {
        this.newsHeadLine = newsHeadLine;
        this.newsUrl = newsUrl;
        this.newsDate = newsDate;
        this.newsContent = newsContent;
    }
}
