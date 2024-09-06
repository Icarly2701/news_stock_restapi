package mini.news.stock.repository.news;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.news.stock.domain.news.SearchKeywordNews;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class CustomSearchKeywordNewsImpl implements CustomSearchKeywordNewsRepository{

    private final RedisTemplate<String, Object> redisTemplate;
    private final String redisHashKey = "SearchKeywordNews";

    @Override
    public List<SearchKeywordNews> findByKeywordInHeadline(String keyword) {
        List<SearchKeywordNews> findAllNews = redisTemplate.opsForHash()
                .values(redisHashKey)
                .stream()
                .map(o -> (SearchKeywordNews) o)
                .toList();

        return findAllNews.stream()
                .filter(news -> news.getNewsHeadLine().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchKeywordNews> findByKeywordInContent(String keyword) {
        List<SearchKeywordNews> findAllNews = redisTemplate.opsForHash()
                .values(redisHashKey)
                .stream()
                .map(o -> (SearchKeywordNews) o)
                .toList();

        return findAllNews.stream()
                .filter(news -> news.getNewsContent().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchKeywordNews> findByKeywordInHeadlineOrContent(String keyword) {

        List<String> redisKeyList = redisTemplate.opsForSet()
                .members(redisHashKey)
                .stream()
                .map(o -> "SearchKeywordNews:" + o)
                .toList();

        List<SearchKeywordNews> findAllNews = redisKeyList.stream()
                .map(redisKey -> redisTemplate.opsForHash().entries(redisKey))
                .map(redisMap -> {
                    String newsHeadLine = (String) redisMap.get("newsHeadLine");
//                    Date newsDate =(Date) redisMap.get("newsDate");
                    String newsUrl = (String) redisMap.get("newsUrl");
                    String newsContent = (String) redisMap.get("newsContent");
                    return new SearchKeywordNews(newsHeadLine, newsUrl, new Date(), newsContent);
                })
                .collect(Collectors.toList());

        List<SearchKeywordNews> collect = findAllNews.stream()
                .filter(news -> news.getNewsHeadLine().contains(keyword) ||
                        news.getNewsContent().contains(keyword))
                .collect(Collectors.toList());

        return findAllNews.stream()
                .filter(news -> news.getNewsHeadLine().contains(keyword) ||
                        news.getNewsContent().contains(keyword))
                .collect(Collectors.toList());
    }
}
