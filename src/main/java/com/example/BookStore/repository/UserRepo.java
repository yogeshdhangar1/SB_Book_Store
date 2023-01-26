package com.example.BookStore.repository;

import com.example.BookStore.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {
    @Query(value="select * from user_model where email =:email",nativeQuery = true)
    Optional<UserModel> findByEmail(String email);
    @Query(value="delete from user_details where email =:email",nativeQuery = true)
    void deleteByEmail(String email);
}
