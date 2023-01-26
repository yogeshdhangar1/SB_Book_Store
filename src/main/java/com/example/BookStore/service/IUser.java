package com.example.BookStore.service;

import com.example.BookStore.dto.ChangePasswordDTO;
import com.example.BookStore.dto.LoginDTO;
import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.model.UserModel;

import java.util.List;

public interface IUser {
    public String  registerUser(UserDTO userdto);

    public UserModel userLogin(LoginDTO logindto);

    public UserModel changePassword(ChangePasswordDTO passwordDTO);

    public List<UserModel> getAllRecords();

      public UserModel  getRecord(Integer id);

      public UserModel updateRecord(Integer id, UserDTO userdto);

    public UserModel getUserByEmailId(String email);

    public String  deleteUserByToken(String token);
}
