package com.example.blog.Repository;

import com.example.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserById(Integer id);


    @Query("SELECT CASE WHEN EXISTS (select u from User u where u.userName = ?1 and u.password = ?2) then cast(1 as boolean ) else cast(0 as boolean) end")
    Boolean loginUser(String userName, String password);



}
