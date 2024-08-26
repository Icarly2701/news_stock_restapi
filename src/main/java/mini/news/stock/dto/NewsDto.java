package mini.news.stock.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class NewsDto {
    public Long newsId;
    public String newsHeadline;
    public String newsUrl;
    public Date newsDate;
    public String newsContent;
}
