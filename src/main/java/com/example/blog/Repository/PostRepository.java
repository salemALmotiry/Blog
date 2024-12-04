package com.example.blog.Repository;

import com.example.blog.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostByPostId(Integer postId);


    List<Post> findAllByCategoryId(Integer categoryId);


    List<Post> findAllByUserId(Integer userId);


    @Query("SELECT p FROM Post p ORDER BY p.publishDate DESC")
    List<Post> findRecentPosts();

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
    List<Post> findPostsByTitleContaining(String keyword);

    @Query("SELECT p FROM Post p WHERE p.title = ?1")
    Post findPostByTitle(String title);

    @Query("SELECT p FROM Post p WHERE p.publishDate < ?1")
    List<Post> findAllPostsBeforeDate(LocalDateTime date);



}
