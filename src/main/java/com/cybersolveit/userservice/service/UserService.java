package com.cybersolveit.userservice.service;


import com.cybersolveit.userservice.dto.UserDto;
import com.cybersolveit.userservice.model.User;
import com.cybersolveit.userservice.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public User saveUser(UserDto userDto){
        // business login
        System.out.println("inside service");
        User user= new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return userRepo.save(user);
    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public Optional<User> getUserById(Long userId) {
        System.out.println("Searching the user with id: "+userId);
        log.info("Searching the user with id: {}",userId);
        return userRepo.findById(userId);
    }

    public User updateUser(Long userId, UserDto userDto) {

        /// get the user with the help of id
        Optional<User> existingUserOptional = userRepo.findById(userId);
        if(existingUserOptional.isPresent()){
           User existingUser= existingUserOptional.get();
           existingUser.setLastName(userDto.getLastName());
           existingUser.setEmail(userDto.getEmail());
           existingUser.setFirstName(userDto.getFirstName());
           return userRepo.save(existingUser);
        }else{
            log.info("User not found in the database with id: "+userId);
            return null;
        }


    }

    public String deleteUser(Long userId) {
       Optional<User> userExist=userRepo.findById(userId);

       if(userExist.isPresent()){
           userRepo.deleteById(userId);
           return "User deleted Successfully";

       }else {
           return "User not found in database";
       }
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
