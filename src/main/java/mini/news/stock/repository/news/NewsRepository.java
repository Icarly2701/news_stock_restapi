package mini.news.stock.repository.news;

import mini.news.stock.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, CustomNewsRepository {

    @Query(value = "INSERT INTO news (news_url, news_headline, news_date, news_view_count, news_recommend_count, news_content) " +
            "VALUES (:newsUrl, :newsHeadline, :newsDate, :newsViewCount, :newsRecommendCount, :newsContent) " +
            "ON DUPLICATE KEY UPDATE " +
            "news_headline = VALUES(news_headline), " +
            "news_date = VALUES(news_date), " +
            "news_view_count = VALUES(news_view_count), " +
            "news_recommend_count = VALUES(news_recommend_count), " +
            "news_content = VALUES(news_content)", nativeQuery = true)
    void upsertNews(@Param("newsUrl") String newsURL,
                    @Param("newsHeadline") String newsHeadline,
                    @Param("newsDate") Date newsDate,
                    @Param("newsViewCount") int newsViewCount,
                    @Param("newsRecommendCount") int newsRecommendCount,
                    @Param("newsContent") String newsContent);

    boolean existsByNewsUrl(String newsUrl);
}
