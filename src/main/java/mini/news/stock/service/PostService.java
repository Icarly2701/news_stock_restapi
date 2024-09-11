package mini.news.stock.service;

import lombok.RequiredArgsConstructor;
import mini.news.stock.domain.post.Post;
import mini.news.stock.domain.post.PostComment;
import mini.news.stock.domain.user.User;
import mini.news.stock.dto.AddPostDto;
import mini.news.stock.dto.PostDto;
import mini.news.stock.dto.PostListDto;
import mini.news.stock.dto.UpdatePostDto;
import mini.news.stock.repository.PostCommentRepository;
import mini.news.stock.repository.PostRepository;
import mini.news.stock.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final UserRepository userRepository;

    public PostDto getPostDetail(Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        List<PostComment> postCommentList = postCommentRepository.findByPostId(postId);
        List<PostDto.PostCommentDto> postCommentDtoList = new ArrayList<>();

        for(PostComment postComment : postCommentList){
            postCommentDtoList.add(PostDto.PostCommentDto.builder()
                    .postCommentDate(postComment.getPostCommentDate())
                    .postCommentDepth(postComment.getPostCommentDepth())
                    .postCommentGroup(postComment.getPostCommentGroup())
                    .postCommentNickname(postComment.getUser().getNickname())
                    .postCommentContent(postComment.getPostCommentContent())
                    .postCommentParentId(postComment.getParentPostComment().getPostCommentId())
                    .postCommentRecommend(postComment.getPostCommentRecommendCount())
                    .postCommentId(postComment.getPostCommentId())
                    .build()
            );
        }

        return PostDto.builder()
                .postContent(post.getPostContent())
                .postCommentList(postCommentDtoList)
                .postViews(post.getPostViewCount())
                .postRecommend(post.getPostRecommendCount())
                .postNickname(post.getUser().getNickname())
                .postHeadline(post.getPostTitle())
                .postDate(post.getPostDate())
                .postId(post.getPostId())
                .build();
    }

    public PostListDto getPostList(int searchPage){
        Page<Post> postList = postRepository.findPage(PageRequest.of(searchPage - 1, 10));
        List<PostListDto.SimplePostDto> postListDto = new ArrayList<>();

        for(Post post : postList.getContent()){
            postListDto.add(PostListDto.SimplePostDto.builder()
                    .postContent(post.getPostContent())
                    .postDate(post.getPostDate())
                    .postHeadline(post.getPostTitle())
                    .postNickname(post.getUser().getNickname())
                    .postRecommend(post.getPostRecommendCount())
                    .postViews(post.getPostViewCount())
                    .build());
        }

        return PostListDto.builder()
                .postList(postListDto)
                .build();
    }
    
    public Long addPost(AddPostDto addPostDto){
        String userUsername = addPostDto.getUserId();
        User user = userRepository.findByUsername(userUsername);
        Post post = postRepository.save(Post.addPost(user, addPostDto.getPostTitle(), addPostDto.getPostContent()));
        return post.getPostId();
    }

    public void deletePost(Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return;
        postRepository.delete(post);
    }

    public void updatePost(Long postId, UpdatePostDto updatePostDto){
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return;
        post.updatePost(updatePostDto);
    }
}
