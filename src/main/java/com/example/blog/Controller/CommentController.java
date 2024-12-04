package com.example.blog.Controller;

import com.example.blog.ApiResponse.ApiResponse;
import com.example.blog.Model.Comment;
import com.example.blog.Service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/blog/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("New comment successfully added."));
    }

    @PutMapping("/update/comment-id/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment successfully updated."));
    }

    @DeleteMapping("/delete/comment-id/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment successfully deleted."));
    }


    //_______________________________________________________________________________
    @GetMapping("/get-by-post/post-id/{postId}")
    public ResponseEntity getCommentsByPost(@PathVariable Integer postId) {
        return ResponseEntity.status(200).body(commentService.findCommentsByPostId(postId));
    }

    @GetMapping("/get-by-user/user-id/{userId}")
    public ResponseEntity getCommentsByUser(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(commentService.findCommentsByUserId(userId));
    }

    @GetMapping("/get-recent-comments/post-id/{postId}")
    public ResponseEntity getRecentCommentsByPostId(@PathVariable Integer postId) {
        List<Comment> comments = commentService.findRecentCommentsByPostId(postId);
        return ResponseEntity.status(200).body(comments);
    }

    @GetMapping("/get-comment/comment-id/{commentId}")
    public ResponseEntity getCommentById(@PathVariable Integer commentId) {
        Comment comment = commentService.findCommentById(commentId);
        return ResponseEntity.status(200).body(comment);
    }


}
