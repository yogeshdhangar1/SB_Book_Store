package com.example.BookStore.service;

import com.example.BookStore.dto.ChangePasswordDTO;
import com.example.BookStore.dto.LoginDTO;
import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.exception.BookStoreException;
import com.example.BookStore.model.UserModel;
import com.example.BookStore.repository.UserRepo;
import com.example.BookStore.util.EmailSenderService;
import com.example.BookStore.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUser{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailSenderService mailSender;
    @Autowired
    private TokenUtil tokenUtil;
    @Override
    public String  registerUser(UserDTO userdto) {
        Optional<UserModel> user=userRepo.findByEmail(userdto.getEmail());
        if (!user.isEmpty()) {
            throw new BookStoreException("Email is alredy Register ");
        }
        else {
            UserModel newUser = new UserModel(userdto);
            userRepo.save(newUser);
            String message = newUser.getFullName();
            mailSender.sendEmail(userdto.getEmail(), "Account Registration successfully", "Hello" + newUser.getFullName() + " Your Account has been created.");
            return message;
        }
    }

    @Override
    public UserModel userLogin(LoginDTO logindto) {
        Optional<UserModel> newUser = userRepo.findByEmail(logindto.getEmail());
        if(logindto.getEmail().equals(newUser.get().getEmail()) && logindto.getPassword().equals(newUser.get().getPassword())) {
            String token = tokenUtil.createToken(newUser.get().getUserID());
            mailSender.sendEmail(logindto.getEmail(),"Account Sign-up successfully","Hello Your Account has been Loggin Successfully.Your token is " + token );
            log.info("SuccessFully Logged In");
            return newUser.get();
        }
        else {

            throw new BookStoreException("User doesn't exists");

        }
    }

    @Override
    public UserModel changePassword(ChangePasswordDTO passwordDTO) {
        Optional<UserModel> user = userRepo.findByEmail(passwordDTO.getEmail());
        if(user.isEmpty()) {
            throw new BookStoreException("User doesn't exists");
        }
        else {
            if(passwordDTO.getEmail().equals(user.get().getEmail())){
                user.get().setPassword(passwordDTO.getNewPassword());
                userRepo.save(user.get());
                log.info("Password changes successfully");
                mailSender.sendEmail(user.get().getEmail(),"Password Change Successfully"+user.get().getFullName(),"Password is :"+user.get().getPassword());

                return user.get();
            }
            else {
                throw new BookStoreException("Invalid token");
            }
        }
    }
    @Override
    public List<UserModel> getAllRecords() {
            List<UserModel> 	userList = userRepo.findAll();
            log.info("All Record Retrieved Successfully");
            return userList;
    }

    @Override
    public UserModel getRecord(Integer id) {
        Optional<UserModel> 	user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new BookStoreException("User Record doesn't exists");
        }
        else {
            log.info("Record retrieved successfully for id "+id);
            return user.get();
        }
    }

    @Override
    public UserModel updateRecord(Integer id, UserDTO userdto) {
        Optional<UserModel> user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new BookStoreException("User Record doesn't exists");
        }
        else {
            UserModel newUser = new UserModel(id,userdto);
            userRepo.save(newUser);
            log.info("User data updated successfully");
            return newUser;
        }
    }

    @Override
    public UserModel getUserByEmailId(String email) {
        Optional<UserModel> newUser = userRepo.findByEmail(email);
        if (newUser.isEmpty()) {
            throw new BookStoreException("User record does not exist");
        } else {
            return newUser.get();
        }
    }
    @Override
    public String deleteUserByToken(String token) {
        Integer userIdToken = tokenUtil.decodeToken(token);
        Optional<UserModel> 	user = userRepo.findById(userIdToken);
        if(user.isEmpty()) {
            throw new BookStoreException("User Record doesn't exists");
        }
        else {
            userRepo.deleteById(user.get().getUserID());
            log.info("Record Delete successfully for given token having id "+userIdToken);
            return "Record Delete successfully for given token id is "+token ;
        }
    }
}
