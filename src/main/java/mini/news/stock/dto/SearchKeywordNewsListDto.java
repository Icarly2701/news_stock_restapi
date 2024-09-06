package mini.news.stock.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
public class SearchKeywordNewsListDto {
    List<SearchKeywordNewsDto> searchKeywordNewsList;

    @Data
    @Builder
    public static class SearchKeywordNewsDto{
        public String newsHeadline;
        public String newsUrl;
        public Date newsDate;
        public String newsContent;
    }
}
