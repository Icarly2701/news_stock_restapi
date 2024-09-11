package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.user.User;
import mini.news.stock.dto.UpdatePostDto;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Date postDate;

    private int postRecommendCount;

    private int postViewCount;

    private String postTitle;

    private String postContent;

    public static Post addPost(User user, String title, String content){
        Post post = new Post();
        post.user = user;
        post.postContent = content;
        post.postViewCount = 0;
        post.postRecommendCount = 0;
        post.postTitle = title;
        return post;
    }

    public void updatePost(UpdatePostDto updatePostDto){
        this.postTitle = updatePostDto.getPostTitle();
        this.postContent = updatePostDto.getPostContent();
    }

    public void addPostView(){
        this.postViewCount++;
    }

    public void addPostLike(){
        this.postRecommendCount++;
    }

    public void addPostDislike(){
        this.postRecommendCount--;
    }
}
