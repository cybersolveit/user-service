package com.cybersolveit.userservice.repo;


import com.cybersolveit.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
     Optional<User> findByEmail(String e);

//     @Query(value = "select * from user_table where email= ?",nativeQuery = true)
//     Optional<User> findUserByEmail(String email);

}
