package mini.news.stock.controller;

import lombok.RequiredArgsConstructor;
import mini.news.stock.dto.AddPostDto;
import mini.news.stock.dto.PostDto;
import mini.news.stock.dto.PostListDto;
import mini.news.stock.dto.UpdatePostDto;
import mini.news.stock.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostDetail(@PathVariable Long postId){
        return new ResponseEntity<>(postService.getPostDetail(postId), HttpStatus.OK);
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

}
