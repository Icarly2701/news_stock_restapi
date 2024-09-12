package mini.news.stock.service;

import lombok.RequiredArgsConstructor;
import mini.news.stock.domain.post.Post;
import mini.news.stock.domain.post.PostComment;
import mini.news.stock.domain.user.User;
import mini.news.stock.repository.PostCommentRepository;
import mini.news.stock.repository.PostRepository;
import mini.news.stock.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void addPostComment(Authentication authentication, Long postId, Long parentCommentId, String commentContent){
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findByUsername(authentication.getName());

        if(post == null) return;

        List<PostComment> postCommentList = postCommentRepository.findByPostId(postId);

        if(parentCommentId == -1) {
            postCommentRepository.save(PostComment.addComment(post, user, commentContent, postCommentList.size()));
            return;
        }

        PostComment postComment = postCommentRepository.findById(parentCommentId).orElse(null);
        if(postComment == null) return;
        postCommentRepository.save(PostComment.addReplyComment(post, user, commentContent, postComment));
    }

    public void updatePostComment(Long postCommentId, String postCommentContent){
        PostComment postComment = postCommentRepository.findById(postCommentId).orElse(null);
        if(postComment == null) return;
        postComment.updateComment(postCommentContent);
    }

    public void deletePostComment(Long postCommentId){
        PostComment postComment = postCommentRepository.findById(postCommentId).orElse(null);
        if(postComment == null) return;
        postCommentRepository.delete(postComment);
    }

    public void addPostCommentReaction(Long postCommentId, Authentication authentication, Boolean isLiked){
        if(authentication instanceof AnonymousAuthenticationToken) return;

        PostComment postComment = postCommentRepository.findById(postCommentId).orElse(null);
        if(postComment == null) return;

        if(isLiked){
            postComment.addLike();
            return;
        }

        postComment.addDislike();
    }
}
