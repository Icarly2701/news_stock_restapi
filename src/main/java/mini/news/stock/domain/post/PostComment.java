package mini.news.stock.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import mini.news.stock.domain.news.NewsComment;
import mini.news.stock.domain.user.User;

import java.time.LocalDateTime;
import java.util.Date;

import static java.sql.Timestamp.valueOf;

@Entity
@Getter
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private PostComment parentPostComment;

    private Date postCommentDate;

    private String postCommentContent;

    private int postCommentRecommendCount;

    private int postCommentDepth;

    private int postCommentGroup;

    public static PostComment addComment(Post post, User user, String postCommentContent, int postCommentGroup){
        PostComment postComment = new PostComment();
        postComment.post = post;
        postComment.user = user;
        postComment.parentPostComment = null;
        postComment.postCommentDate = valueOf(LocalDateTime.now());
        postComment.postCommentContent = postCommentContent;
        postComment.postCommentRecommendCount = 0;
        postComment.postCommentDepth = 0;
        postComment.postCommentGroup = postCommentGroup;
        return postComment;
    }

    public static PostComment addReplyComment(Post post, User user, String postCommentContent, PostComment parentComment){
        PostComment postComment = new PostComment();
        postComment.post = post;
        postComment.user = user;
        postComment.parentPostComment = parentComment;
        postComment.postCommentDate = valueOf(LocalDateTime.now());
        postComment.postCommentContent = postCommentContent;
        postComment.postCommentRecommendCount = 0;
        postComment.postCommentDepth = parentComment.postCommentDepth + 1;
        postComment.postCommentGroup = parentComment.postCommentGroup;
        return postComment;
    }

    public void updateComment(String postCommentContent){
        this.postCommentContent = postCommentContent;
    }

    public void addLike(){
        this.postCommentRecommendCount++;
    }

    public void addDislike(){
        this.postCommentRecommendCount--;
    }
}
