package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;
import mini.news.stock.dto.TempPostDto;

@Entity
@Getter
public class TempPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tempPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String tempPostTitle;

    private String tempPostContent;

    public static TempPost addTempPost(User user, TempPostDto tempPostDto){
        TempPost tempPost = new TempPost();
        tempPost.tempPostContent = tempPostDto.getPostContent();
        tempPost.tempPostTitle = tempPostDto.getPostTitle();
        tempPost.user = user;
        return tempPost;
    }
}
