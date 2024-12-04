package com.example.blog.Repository;

import com.example.blog.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPostId(Integer postId);

    List<Comment> findAllByUserId(Integer userId);

    Comment findCommentByCommentId(Integer commentId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.postId = ?1")
    Long countCommentsByPostId(Integer postId);

    @Query("SELECT c FROM Comment c WHERE c.postId = ?1 ORDER BY c.commentDate DESC")
    List<Comment> findRecentCommentsByPostId(Integer postId);
}
