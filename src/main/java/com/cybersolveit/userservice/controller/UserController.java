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
//?& ReqeustParam

   // user/search?email=jobi@gmail.com&firstName=jobi&
    ///http://localhost:8082/api/v1/name



    /// another application --- add all dependency
    /// create a model class called student
           // create data members --- studId, studentName, studentCourse, studentEmail
    //create a repo layer
    ///create studentDTO
    //create a student Controller
          // create all the httpMethod we did in user service and queryParams
    // create a service class and create all method that will have business logic
    //    to save,delete, update, and getAllstudent, getStudent, getStudentByEMail


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

    //// upated request, usedId,

// find user from database and update the user with updated dto


    @PutMapping("/user/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody UserDto userDto){
       return userService.updateUser(userId,userDto);
    }


    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);

    }

    @GetMapping("/user/search")
    public User getEmailByUser(@RequestParam String email,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName){
        System.out.println("lastname: "+lastName);
        System.out.println("firstName: "+firstName);

        return  userService.findUserByEmail(email).get();
    }

}
