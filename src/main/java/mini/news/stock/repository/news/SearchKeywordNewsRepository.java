package mini.news.stock.repository.news;

import mini.news.stock.domain.news.SearchKeywordNews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchKeywordNewsRepository extends CrudRepository<SearchKeywordNews, String>{
    List<SearchKeywordNews> findByKeyword(String keyword);
}
