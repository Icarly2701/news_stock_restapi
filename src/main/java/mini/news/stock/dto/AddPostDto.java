package mini.news.stock.dto;

import lombok.Data;

@Data
public class AddPostDto {
    public String postTitle;
    public String postContent;
    public String userId;
}
