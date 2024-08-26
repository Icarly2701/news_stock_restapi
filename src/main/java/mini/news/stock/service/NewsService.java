package mini.news.stock.service;

import lombok.RequiredArgsConstructor;
import mini.news.stock.domain.news.News;
import mini.news.stock.dto.NewsDto;
import mini.news.stock.dto.SearchKeywordNewsDto;
import mini.news.stock.repository.news.NewsRepository;
import mini.news.stock.service.renewaltopic.AutoRenewalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final AutoRenewalService autoRenewalService;

    public NewsDto getNewsDetail(Long newsId){
        News news = newsRepository.findById(newsId).orElse(null);
        assert news != null;
        return NewsDto.builder()
                .newsId(news.getNewsId())
                .newsUrl(news.getNewsUrl())
                .newsHeadline(news.getNewsHeadline())
                .newsContent(news.getNewsContent())
                .newsDate(news.getNewsDate())
                .build();
    }

    public SearchKeywordNewsDto getSearchKeywordNewsDetail(String keyword, String sortIndex){

    }

}
