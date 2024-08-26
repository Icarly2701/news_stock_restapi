package mini.news.stock.domain.news;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash(value = "SearchKeywordNews")
public class SearchKeywordNews {

    @Id
    private String newsId;

    

    @TimeToLive
    private long ttl;
}
