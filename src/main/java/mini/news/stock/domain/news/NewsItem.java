package mini.news.stock.domain.news;

import lombok.Data;

@Data
public class NewsItem {

    private String title;
    private String originallink;
    private String link;
    private String description;
    private String pubDate;
}
