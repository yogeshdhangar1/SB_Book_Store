package com.example.BookStore.service;

import com.example.BookStore.dto.CartDTO;
import com.example.BookStore.model.CartModel;

import java.util.List;

public interface ICart {
    public CartModel insertCart(CartDTO cartdto);

    public List<CartModel> getAllCartRecords();

    public CartModel getCartRecord(Integer id);

    public CartModel updateCartRecord(Integer id, CartDTO cartdto);

    public CartModel deleteCartRecord(Integer id);

    List<CartModel> deleteAllFromCart();
}
