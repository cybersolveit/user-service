package com.cybersolveit.userservice.service;


import com.cybersolveit.userservice.dto.ApiResponse;
import com.cybersolveit.userservice.dto.StudentDto;
import com.cybersolveit.userservice.dto.StudentResponse;
import com.cybersolveit.userservice.dto.UserDto;
import com.cybersolveit.userservice.exception.UserNotFoundException;
import com.cybersolveit.userservice.exception.UserServiceException;
import com.cybersolveit.userservice.model.User;
import com.cybersolveit.userservice.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private RestTemplate restTemplate;
    public ApiResponse saveUser (UserDto userDto){
        // business login
        System.out.println("inside service");
        ApiResponse apiResponse= new ApiResponse();
        User user= new User();
        Optional<User> userByEmail = userRepo.findByEmail(userDto.getEmail());
        // if user is present then returh some error
        // if not then only save the user
        if(userByEmail.isPresent()){
//            return error set the  erorr in api response
//            return null;
            //swagger or open api

        } else {

            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            try {
                Map<String,Object> responseHashmap= new HashMap<>();

                User savedUser = userRepo.save(user);

//                *************************
                StudentDto studentDto= new StudentDto();
                studentDto.setEmail(savedUser.getEmail());
                studentDto.setCourse(userDto.getCourse());
                studentDto.setAddedBy(userDto.getEmail());
                studentDto.setFirstName(savedUser.getFirstName());
                studentDto.setLastName(savedUser.getLastName());
                /// "{
                //    "studentId": 4,
                //    "email": "nab1@gmail.com",
                //    "firstName": "Nabin1",
                //    "lastName": "Singh1",
                //    "course": "Java",
                //    "addedBy": null
                //}

                ResponseEntity<StudentResponse> studentResponseEntity = restTemplate.postForEntity("http://localhost:8082/students", studentDto, StudentResponse.class);
                if(studentResponseEntity.getStatusCode()==HttpStatus.OK){
                    StudentResponse studentResponse= studentResponseEntity.getBody();
                    responseHashmap.put("student",studentResponse);
                    responseHashmap.put("user",savedUser);

                }


//                        *******************
                apiResponse.setData(responseHashmap);
                apiResponse.setMessage("User with id " + savedUser.getUser_id() + " created successfully.");
                apiResponse.setStatusCode(HttpStatus.OK.value());

            } catch (Exception e) {

                // throw some exception
                throw new UserServiceException(e.getMessage());


            }
        }
        return apiResponse;

    }


    public ApiResponse getAllUsers() {
        log.info("before break point");
        ApiResponse apiResponse= new ApiResponse();
        log.info("after break point");
        List<User> users= userRepo.findAll();
        if(users.isEmpty()){
            apiResponse.setErrors("No Users available");
            apiResponse.setMessage("No Users available");
            apiResponse.setStatusCode(HttpStatus.OK.value());
        } else {
            apiResponse.setMessage(users.size()+ " users are available");
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setData(users);
        }
        return apiResponse;
    }


    public ApiResponse getUserById(Long userId) {
        log.info("Searching the user with id: {}",userId);
        ApiResponse apiResponse= new ApiResponse();
        Optional<User> userById=userRepo.findById(userId);
        if(userById.isPresent()){
            apiResponse.setData(userById);
            apiResponse.setMessage("User fetched successfully with id: "+
                    userById.get().getUser_id());
            apiResponse.setStatusCode(HttpStatus.OK.value());
        } else{
            log.info("exception occur");
            throw new UserNotFoundException("User with "+userId+ " not found");


        }
        return apiResponse;
    }

    public ApiResponse updateUser(Long userId, UserDto userDto) {

        /// get the user with the help of id
        ApiResponse apiResponse= new ApiResponse();
        Optional<User> existingUserOptional = userRepo.findById(userId);
        if(existingUserOptional.isPresent()){
           User existingUser= existingUserOptional.get();
           existingUser.setLastName(userDto.getLastName());
           existingUser.setEmail(userDto.getEmail());
           existingUser.setFirstName(userDto.getFirstName());
           User updatedUser=userRepo.save(existingUser);
           apiResponse.setStatusCode(HttpStatus.OK.value());
           apiResponse.setData(updatedUser);
           apiResponse.setMessage("user with id "+ userId+" updated successfully");
        }else{
            log.info("User not found in the database with id: "+userId);
            throw new UserNotFoundException("User not found in the database with id: "+userId);
        }
        return apiResponse;


    }

    public ApiResponse deleteUser(Long userId) {
        ApiResponse apiResponse = new ApiResponse();
       Optional<User> userExist=userRepo.findById(userId);

       if(userExist.isPresent()){
           userRepo.deleteById(userId);
           apiResponse.setMessage("User deleted Successfully with id: "+userId);
           apiResponse.setStatusCode(HttpStatus.OK.value());

       }else {
          throw new UserNotFoundException("User not Found");
       }
       return apiResponse;
    }

    public ApiResponse findUserByEmail(String email) {
        ApiResponse apiResponse= new ApiResponse();
        Optional<User> userByEmail=userRepo.findByEmail(email);;
        if(userByEmail.isPresent()){
            apiResponse.setData(userByEmail);
            apiResponse.setMessage("User fetched successfully with email: "+email);
            apiResponse.setStatusCode(HttpStatus.OK.value());
        } else{
            log.info("exception occur");
            throw new UserNotFoundException("User with "+email+ " not found");
        }
        return apiResponse;
    }
}
