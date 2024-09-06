package mini.news.stock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.news.stock.domain.news.News;
import mini.news.stock.domain.news.SearchKeywordNews;
import mini.news.stock.dto.NewsDto;
import mini.news.stock.dto.SearchKeywordNewsListDto;
import mini.news.stock.repository.news.CustomSearchKeywordNewsRepository;
import mini.news.stock.repository.news.NewsRepository;
import mini.news.stock.repository.news.SearchKeywordNewsRepository;
import mini.news.stock.service.renewaltopic.AutoRenewalService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class NewsService {

    private final NewsRepository newsRepository;
    private final CustomSearchKeywordNewsRepository customSearchKeywordNewsRepository;
    private final SearchKeywordNewsRepository searchKeywordNewsRepository;
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

    public List<SearchKeywordNewsListDto.SearchKeywordNewsDto> getSearchKeywordNewsDetail(String keyword, String sortIndex){
        List<SearchKeywordNews> searchKeywordNewsList = customSearchKeywordNewsRepository.findByKeywordInHeadlineOrContent(keyword);
        if(searchKeywordNewsList.isEmpty()){
            autoRenewalService.searchKeywordNews(keyword);
            searchKeywordNewsList = customSearchKeywordNewsRepository.findByKeywordInHeadlineOrContent(keyword);
        }

        List<SearchKeywordNewsListDto.SearchKeywordNewsDto> searchKeywordNewsDto = new ArrayList<>();
        for(SearchKeywordNews searchKeywordNews : searchKeywordNewsList){
            searchKeywordNewsDto.add(SearchKeywordNewsListDto.SearchKeywordNewsDto.builder()
                    .newsUrl(searchKeywordNews.getNewsUrl())
                    .newsContent(searchKeywordNews.getNewsContent())
                    .newsHeadline(searchKeywordNews.getNewsHeadLine())
                    .newsDate(searchKeywordNews.getNewsDate())
                    .build()
            );
        }

        return searchKeywordNewsDto;
    }

}
