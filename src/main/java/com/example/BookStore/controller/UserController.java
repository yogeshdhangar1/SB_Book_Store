package com.example.BookStore.controller;

import com.example.BookStore.dto.ChangePasswordDTO;
import com.example.BookStore.dto.LoginDTO;
import com.example.BookStore.dto.ResponseDTO;
import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userservice")
@CrossOrigin
public class UserController {

    @Autowired
    private IUser service;
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO userdto){
        ResponseDTO dto = new ResponseDTO("User Record created successfully !",service.registerUser(userdto));
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@Valid @RequestBody LoginDTO logindto){
        ResponseDTO dto = new ResponseDTO("User logged in successfully !",service.userLogin(logindto));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @PutMapping("/changepassword")
    public ResponseEntity<ResponseDTO> changePassword(@Valid @RequestBody ChangePasswordDTO passwordDTO){
        ResponseDTO dto = new ResponseDTO("Password Resetted successfully !",service.changePassword(passwordDTO));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @GetMapping("/retrieveAll")
    public ResponseEntity<ResponseDTO> getAllRecords(){
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",service.getAllRecords());
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ResponseDTO> getRecord(@PathVariable Integer id){
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",service.getRecord(id));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateRecord(@PathVariable Integer id, @Valid @RequestBody UserDTO userdto){
        ResponseDTO dto = new ResponseDTO("Record updated successfully !",service.updateRecord(id,userdto));
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/retrieveByemail/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable String email) {
        ResponseDTO responseDTO = new ResponseDTO("Record for email successfully", service.getUserByEmailId(email));
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/deletebytoken/{token}")
    public ResponseEntity<ResponseDTO> deleteRecordByToken(@PathVariable String token) {
        ResponseDTO dto= new ResponseDTO("Record Delete Successfully !",service.deleteUserByToken(token));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
}
