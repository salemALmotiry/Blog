package com.example.blog.Controller;

import com.example.blog.ApiResponse.ApiResponse;
import com.example.blog.Model.Post;
import com.example.blog.Service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/blog/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("New post successfully added."));
    }

    @PutMapping("/update/post-id/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post successfully updated."));
    }

    @DeleteMapping("/delete/post-id/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post successfully deleted."));
    }


  //_________________________________________________________________
    @GetMapping("/get-by-category/category-id/{categoryId}")
    public ResponseEntity getPostsByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.status(200).body(postService.getPostsByCategory(categoryId));
    }

    @GetMapping("/get-by-user/user-id/{userId}")
    public ResponseEntity getPostsByUser(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(postService.getPostsByUser(userId));
    }

    @GetMapping("/get-recent")
    public ResponseEntity getRecentPosts() {
        return ResponseEntity.status(200).body(postService.getRecentPosts());
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity search(@PathVariable String keyword) {
        return ResponseEntity.status(200).body(postService.search(keyword));
    }

    @GetMapping("/get-by-title/title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title) {
        Post post = postService.getPostByTitle(title);
        return ResponseEntity.status(200).body(post);
    }

    @GetMapping("/get-before-date/date/{date}")
    public ResponseEntity getAllPostsBeforeDate(@PathVariable String date) {
        List<Post> posts = postService.getAllPostsBeforeDate(date);
        return ResponseEntity.status(200).body(posts);
    }

    @GetMapping("/get-by-category-name/category-name/{catName}")
    public ResponseEntity<List<Post>> getPostByCategoryName(@PathVariable String catName) {
        List<Post> posts = postService.getPostByCategoryName(catName);
        if (posts.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(posts);
    }




}
