package mini.news.stock.controller;

import lombok.RequiredArgsConstructor;
import mini.news.stock.service.PostCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostCommentController {

    private final PostCommentService postCommentService;

    @PostMapping("/post/{postId}/comment/add")
    public ResponseEntity<String> addPostComment(@PathVariable Long postId,
                                         @RequestParam("parentCommentId") Long parentCommentId,
                                         @RequestParam("commentContent") String commentContent,
                                         Authentication authentication){
        postCommentService.addPostComment(authentication, postId, parentCommentId, commentContent);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시물 댓글 추가 완료");
    }

    @PostMapping("/post/comment/update/{postCommentId}")
    public ResponseEntity<String> updatePostComment(@PathVariable Long postCommentId,
                                                    @RequestParam String postCommentContent){
        postCommentService.updatePostComment(postCommentId, postCommentContent);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 댓글 수정 완료");
    }

    @DeleteMapping("/post/comment/delete/{postCommentId}")
    public ResponseEntity<String> deletePostComment(@PathVariable Long postCommentId){
        postCommentService.deletePostComment(postCommentId);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 댓글 수정 완료");
    }

    @GetMapping("/post/comment/{postCommentId}/reaction")
    public ResponseEntity<String> addPostCommentReaction(@PathVariable Long postCommentId,
                                                         @RequestParam Boolean isLiked,
                                                         Authentication authentication){
        postCommentService.addPostCommentReaction(postCommentId, authentication, isLiked);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 댓글 반응 완료");
    }
}
