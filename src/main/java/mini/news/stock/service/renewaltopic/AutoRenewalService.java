package mini.news.stock.service.renewaltopic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.news.stock.domain.news.NewsItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Scheduled(fixedDelay = 720000)
    public void renewalNewsData(){
        String [] stockKeyword = {"주식", "증권", "금융", "코스피", "코스닥", "나스닥", "NASDAQ", "다우존스", "금리", "증시", "부동산"};
        for(String keyword : stockKeyword){
            getNewsDataUseApi(keyword);
        }
    }

    private void getNewsDataUseApi(String keyword) {
        Map<String, Object> recentNewsAboutStock = newsClient.getNews(keyword, display, sort);
        Object itemsObject = recentNewsAboutStock.get("items");
        if(itemsObject instanceof List<?> itemsList){
            if(!itemsList.isEmpty() && itemsList.get(0) instanceof Map){
                List<Map<String, Object>> items = castListOfMaps(itemsList);
                List<NewsItem> newsItems = objectMapper.convertValue(items, new TypeReference<List<NewsItem>>() {});
                log.info("newsItems: {}", newsItems.get(0));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> castListOfMaps(List<?> list) {
        return (List<Map<String, Object>>) list;
    }
}
