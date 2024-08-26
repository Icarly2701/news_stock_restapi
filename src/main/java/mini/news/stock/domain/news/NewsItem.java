package mini.news.stock.domain.news;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.util.Date;

@Getter
@Setter
public class NewsItem {

    private String title;
    private String originallink;
    private String link;
    private String description;
    private Date pubDate;

    public void cleanHtmlCode(){
        if(description != null){
            description = Jsoup.clean(description, Safelist.none());
        }

        if(title != null){
            title = Jsoup.clean(title, Safelist.none());
        }
    }
}
