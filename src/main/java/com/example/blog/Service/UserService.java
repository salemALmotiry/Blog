package com.example.blog.Service;

import com.example.blog.ApiResponse.ApiException;
import com.example.blog.Model.User;
import com.example.blog.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;



    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        User oldUser = userRepository.findUserById(userId);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(oldUser);

    }

    public void updateUser(Integer userId,User user) {

        User oldUser = userRepository.findUserById(userId);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }

        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setUserName(user.getUserName());


        userRepository.save(oldUser);

    }

    public User findUserById(Integer userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user;
    }

}
