package com.llhtdqc.spring_boot_3.Service;

import com.llhtdqc.spring_boot_3.DTO.Request.UserCreationRequest;
import com.llhtdqc.spring_boot_3.DTO.Request.UserUpdateRequest;
import com.llhtdqc.spring_boot_3.Entity.User;
import com.llhtdqc.spring_boot_3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException(" 'userName' already existed, please enter another userName.");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassWord(request.getPassWord());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest update){
        User user = getUser(userId);

        user.setUserName(update.getUserName());
        user.setPassWord(update.getPassWord());
        user.setFirstName(update.getFirstName());
        user.setLastName(update.getLastName());

        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
