package com.example.blog.Service;

import com.example.blog.ApiResponse.ApiException;
import com.example.blog.Model.Comment;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;
import com.example.blog.Repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        User user = userService.findUserById(comment.getUserId());
        Post post = postService.getPostById(comment.getPostId());

        commentRepository.save(comment);
    }

    public void updateComment(Integer commentId, Comment comment) {
        Comment oldComment = commentRepository.findCommentByCommentId(commentId);
        User user = userService.findUserById(comment.getUserId());
        Post post = postService.getPostById(comment.getPostId());

        if (oldComment == null) {
            throw new ApiException("Comment not found");
        }

        oldComment.setContent(comment.getContent());
        oldComment.setCommentDate(comment.getCommentDate());

        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer commentId) {
        Comment oldComment = commentRepository.findCommentByCommentId(commentId);
        if (oldComment == null) {
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(oldComment);
    }


    //________________________________________________________________

    public List<Comment> findCommentsByPostId(Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public List<Comment> findCommentsByUserId(Integer userId) {
        return commentRepository.findAllByUserId(userId);
    }


    public List<Comment> findRecentCommentsByPostId(Integer postId) {
        return commentRepository.findRecentCommentsByPostId(postId);
    }

    public Comment findCommentById(Integer commentId) {
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        if (comment == null) {
            throw new ApiException("Comment not found");
        }
        return comment;
    }
}
