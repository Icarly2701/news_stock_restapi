package mini.news.stock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
public class PostDto {
    public Long postId;
    public String postHeadline;
    public String postContent;
    public String postNickname;
    public int postRecommend;
    public int postViews;
    public Date postDate;
    List<PostCommentDto> postCommentList;

    @Data
    @Builder
    public static class PostCommentDto{
        public Long postCommentId;
        public String postCommentNickname;
        public String postCommentContent;
        public int postCommentRecommend;
        public Date postCommentDate;
        public int postCommentDepth;
        public int postCommentGroup;
        public Long postCommentParentId;
    }
}
