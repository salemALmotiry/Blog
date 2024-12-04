package com.example.blog.Service;

import com.example.blog.ApiResponse.ApiException;
import com.example.blog.Model.Category;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;
import com.example.blog.Repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
        User user = userService.findUserById(post.getUserId());

        Category category = categoryService.getCategoryById(post.getCategoryId());

        postRepository.save(post);
    }


    public void updatePost(Integer postId, Post post) {
        Post oldPost = postRepository.findPostByPostId(postId);
        User user = userService.findUserById(post.getUserId());

        Category category = categoryService.getCategoryById(post.getCategoryId());

        if (oldPost == null) {
            throw new ApiException("Post not found");
        }
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setPublishDate(post.getPublishDate());
        postRepository.save(oldPost);
    }

    public void deletePost(Integer postId) {
        Post oldPost = postRepository.findPostByPostId(postId);
        if (oldPost == null) {
            throw new ApiException("Post not found");
        }
        postRepository.delete(oldPost);
    }


    //______________________________________________________________________-

    public List<Post> getPostsByCategory(Integer categoryId) {
        return postRepository.findAllByCategoryId(categoryId);
    }

    public List<Post> getPostsByUser(Integer userId) {
        return postRepository.findAllByUserId(userId);
    }

    public List<Post> getRecentPosts() {
        return postRepository.findRecentPosts();
    }

    public List<Post> search(String keyword) {

        return postRepository.findPostsByTitleContaining(keyword);
    }

    public Post getPostByTitle(String title) {
        Post post = postRepository.findPostByTitle(title);
        if (post == null) {
            throw new ApiException("Post with the given title not found");
        }
        return post;
    }

    public List<Post> getAllPostsBeforeDate(String date) {
        LocalDateTime parsedDate;
        try {
            parsedDate = LocalDateTime.parse(date);
        } catch (Exception e) {
           throw new ApiException ("Invalid date format. Use 'yyyy-MM-ddTHH:mm:ss' format.");
        }

        List<Post> posts = postRepository.findAllPostsBeforeDate(parsedDate);
        if (posts.isEmpty()) {
            throw new ApiException("No posts found before the specified date.");
        }
        return posts;
    }

    public Post getPostById(Integer postId) {
        Post post = postRepository.findPostByPostId(postId);
        if (post == null) {
            throw new ApiException("Post not found");
        }
        return post;
    }


}
