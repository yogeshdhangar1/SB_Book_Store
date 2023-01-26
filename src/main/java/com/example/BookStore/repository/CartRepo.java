package com.example.BookStore.repository;

import com.example.BookStore.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartModel,Integer> {

}
