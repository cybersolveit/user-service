package com.cybersolveit.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_db")
@Data
//@Getter
//@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    @Column()
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

//    public Long getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Long user_id) {
//        this.user_id = user_id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
}
