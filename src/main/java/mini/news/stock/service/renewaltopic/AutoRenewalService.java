package mini.news.stock.service.renewaltopic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.news.stock.domain.news.News;
import mini.news.stock.domain.news.NewsItem;
import mini.news.stock.domain.news.SearchKeywordNews;
import mini.news.stock.repository.news.NewsRepository;
import mini.news.stock.repository.news.SearchKeywordNewsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AutoRenewalService {

    @Value("${api.query.sort}")
    private String sort;

    @Value("${api.query.display}")
    private int display;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NewsClient newsClient;
    private final NewsRepository newsRepository;
    private final SearchKeywordNewsRepository searchKeywordNewsRepository;


    @Transactional
    @Scheduled(fixedDelay = 720000)
    public void renewalNewsData(){
        String [] stockKeyword = {"주식", "증권", "금융", "코스피", "코스닥", "나스닥", "NASDAQ", "다우존스", "금리", "증시", "부동산", "주가"};
        for(String keyword : stockKeyword){
            getNewsDataUseApi(keyword, 1);
        }
    }

    @Transactional
    public void searchKeywordNews(String keyword){
        Map<String, Object> recentNewsAboutStock = newsClient.getNews(keyword, display, sort);
        Object itemsObject = recentNewsAboutStock.get("items");
        if(itemsObject instanceof List<?> itemsList){
            if(!itemsList.isEmpty() && itemsList.get(0) instanceof Map){
                List<Map<String, Object>> items = castListOfMaps(itemsList);
                List<NewsItem> newsItems = objectMapper.convertValue(items, new TypeReference<List<NewsItem>>() {});
                getSaveSearchKeywordNews(newsItems);
            }
        }
    }

    private void getNewsDataUseApi(String keyword, int way) {
        Map<String, Object> recentNewsAboutStock = newsClient.getNews(keyword, display, sort);
        Object itemsObject = recentNewsAboutStock.get("items");
        if(itemsObject instanceof List<?> itemsList){
            if(!itemsList.isEmpty() && itemsList.get(0) instanceof Map){
                List<Map<String, Object>> items = castListOfMaps(itemsList);
                List<NewsItem> newsItems = objectMapper.convertValue(items, new TypeReference<List<NewsItem>>() {});
                saveNews(newsItems);
            }
        }
    }

    private void saveNewsUseUpsert(List<NewsItem> newsItems) {
        for(NewsItem newsItem : newsItems){
            newsItem.cleanHtmlCode();
            newsRepository.upsertNews(newsItem.getOriginallink(),
                    newsItem.getTitle(),
                    newsItem.getPubDate(),
                    0,
                    0,
                    newsItem.getDescription());
        }
    }

    private void saveNews(List<NewsItem> newsItems){
        for(NewsItem newsItem : newsItems){
            newsItem.cleanHtmlCode();
            String rink = newsItem.getOriginallink() != null ? newsItem.getOriginallink() : newsItem.getLink();
            if(!newsRepository.existsByNewsUrl(rink)){
                newsRepository.save(News.makeNewsEntity(newsItem));
            }
        }
    }

    private void getSaveSearchKeywordNews(List<NewsItem> newsItems) {

        log.info("확인용: {}", newsItems.get(0).getTitle());
        for(NewsItem newsItem : newsItems){
            searchKeywordNewsRepository.save(new SearchKeywordNews(
                    newsItem.getTitle(),
                    newsItem.getOriginallink(),
                    newsItem.getPubDate(),
                    newsItem.getDescription())
            );
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> castListOfMaps(List<?> list) {
        return (List<Map<String, Object>>) list;
    }
}
