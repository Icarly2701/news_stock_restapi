package mini.news.stock.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TempPostDto {
    public String postTitle;
    public String postContent;
}
