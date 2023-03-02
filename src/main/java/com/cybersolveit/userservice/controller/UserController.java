package com.cybersolveit.userservice.controller;


import com.cybersolveit.userservice.dto.UserDto;
import com.cybersolveit.userservice.model.User;
import com.cybersolveit.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

//    C-> create -> @PostMapping ---
//    R-> Read -> @GetMapping ---
//    U-> update -> @PutMapping
//    D-> Delete -> @DeleteMapping

    // get By id --

    ///http://localhost:8082/api/v1/name


    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public String getName(){
        return  "Hello from spring boot";
    }


    //method to save the user
    // url http://localhost:8082/api/v1/user
    @PostMapping("/user")
    public User createUser(@RequestBody UserDto userDto){
        System.out.printf("inside controller");
       return userService.saveUser(userDto);

    }


    /**
     * > This function will return a list of all users in the database
     */
    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    ///GET user by ID

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId).get();
    }





}
