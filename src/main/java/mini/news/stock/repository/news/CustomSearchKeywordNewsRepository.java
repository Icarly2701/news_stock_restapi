package mini.news.stock.repository.news;

import mini.news.stock.domain.news.SearchKeywordNews;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomSearchKeywordNewsRepository {
    List<SearchKeywordNews> findByKeywordInHeadline(String keyword);
    List<SearchKeywordNews> findByKeywordInContent(String keyword);
    List<SearchKeywordNews> findByKeywordInHeadlineOrContent(String keyword);

}
