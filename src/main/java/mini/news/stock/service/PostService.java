package mini.news.stock.service;

import lombok.RequiredArgsConstructor;
import mini.news.stock.domain.custom.Reaction;
import mini.news.stock.domain.post.Post;
import mini.news.stock.domain.post.PostComment;
import mini.news.stock.domain.post.ReactionPost;
import mini.news.stock.domain.post.RecentPost;
import mini.news.stock.domain.user.User;
import mini.news.stock.dto.AddPostDto;
import mini.news.stock.dto.PostDto;
import mini.news.stock.dto.PostListDto;
import mini.news.stock.dto.UpdatePostDto;
import mini.news.stock.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final RecentPostRepository recentPostRepository;
    private final ReactionPostRepository reactionPostRepository;
    private final PostCommentRepository postCommentRepository;
    private final UserRepository userRepository;

    public PostDto getPostDetail(Long postId, Authentication authentication){
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findByUsername(authentication.getName());

        //조회 수 증가
        if(post == null)return null;
        post.addPostView();

        //사용자가 익명이 아니라면 최근 본 게시판에 추가
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            recentPostRepository.save(RecentPost.addRecentPost(user, post));
        }


        //댓글 가져와서 만들기
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

    public void addPostLike(Long postId, Authentication authentication){
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findByUsername(authentication.getName());
        if(post == null) return;
        post.addPostLike();

        ReactionPost reactionPost = reactionPostRepository.findByUserId(user.getUserId()).orElse(null);
        if(reactionPost == null){
            reactionPostRepository.save(ReactionPost.addReactionGoodPost(user, post));
            return;
        }

        if(reactionPost.getReactionPostReaction() == Reaction.GOOD){
            reactionPostRepository.delete(reactionPost);
        }
    }

    public void addPostDislike(Long postId, Authentication authentication){
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findByUsername(authentication.getName());
        if(post == null) return;
        post.addPostDislike();

        ReactionPost reactionPost = reactionPostRepository.findByUserId(user.getUserId()).orElse(null);
        if(reactionPost == null){
            reactionPostRepository.save(ReactionPost.addReactionBadPost(user, post));
            return;
        }

        if(reactionPost.getReactionPostReaction() == Reaction.BAD){
            reactionPostRepository.delete(reactionPost);
        }
    }
}
