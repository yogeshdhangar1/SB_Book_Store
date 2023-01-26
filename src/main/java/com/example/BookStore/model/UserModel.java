package com.example.BookStore.model;

import com.example.BookStore.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userID;
    private  String fullName;
    private String email;
    private String phoneNumber;
    private String password;



    public UserModel(UserDTO dto) {
        this.fullName = dto.getFullName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.password = dto.getPassword();

    }
    public UserModel(Integer userID, UserDTO dto) {
        this.userID=userID;
        this.fullName = dto.getFullName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.password = dto.getPassword();

    }


}
