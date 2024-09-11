package mini.news.stock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
public class PostListDto {
    List<SimplePostDto> postList;

    @Data
    @Builder
    public static class SimplePostDto{
        public String postHeadline;
        public String postNickname;
        public Date postDate;
        public int postRecommend;
        public int postViews;
        public String postContent;
    }
}
