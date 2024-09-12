package mini.news.stock.controller;

import feign.Response;
import lombok.RequiredArgsConstructor;
import mini.news.stock.domain.post.TempPost;
import mini.news.stock.dto.*;
import mini.news.stock.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostDetail(@PathVariable Long postId,
                                                 Authentication authentication){
        return new ResponseEntity<>(postService.getPostDetail(postId, authentication), HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<PostListDto> getPostList(@RequestParam("sort_index") String sortIndex,
                                                   @RequestParam("search_page") int searchPage){
        return new ResponseEntity<>(postService.getPostList(searchPage), HttpStatus.OK);
    }

    @PostMapping("/post/add")
    public ResponseEntity<String> addPost(@RequestAttribute AddPostDto addPostDto){
        Long postId = postService.addPost(addPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시물 등록 완료. 게시물 번호: " + postId);
    }

    @DeleteMapping("/post/{postId}/delete")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 삭제 완료");
    }

    @PostMapping("/post/{postId}/update")
    public ResponseEntity<String> updatePost(@PathVariable Long postId,
                                             @RequestAttribute UpdatePostDto updatePostDto){
        postService.updatePost(postId, updatePostDto);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 수정 완료");
    }

    @GetMapping("/post/{postId}/like")
    public ResponseEntity<String> addPostLike(@PathVariable Long postId,
                                              Authentication authentication){
        postService.addPostLike(postId, authentication);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 좋아요 완료");
    }

    @GetMapping("/post/{postId}/dislike")
    public ResponseEntity<String> addPostDislike(@PathVariable Long postId,
                                                 Authentication authentication){
        postService.addPostDislike(postId, authentication);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 싫어요 완료");
    }

    @PostMapping("/post/temp/add")
    public ResponseEntity<String> addTempPost(@RequestAttribute TempPostDto tempPostDto,
                                              Authentication authentication){
        postService.addTempPost(tempPostDto, authentication);
        return ResponseEntity.status(HttpStatus.OK).body("임시 게시물 저장 완료");
    }

    @GetMapping("/post/temp")
    public ResponseEntity<TempPostDto> getTempPost(Authentication authentication){
        return new ResponseEntity<>(postService.getTempPost(authentication), HttpStatus.OK);
    }
}
