package com.example.BookStore.service;

import com.example.BookStore.dto.CartDTO;
import com.example.BookStore.exception.BookStoreException;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.model.CartModel;
import com.example.BookStore.model.UserModel;
import com.example.BookStore.repository.BookRepo;
import com.example.BookStore.repository.CartRepo;
import com.example.BookStore.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICart{
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public CartModel insertCart(CartDTO cartdto) {
        Optional<BookModel> book = bookRepo.findById(cartdto.getBookID());
        Optional<UserModel> user = userRepo.findById(cartdto.getUserID());
        if (book.isPresent() && user.isPresent()) {
            if (cartdto.getQuantity() < book.get().getQuantity()) {
                CartModel newCart = new CartModel(cartdto.getQuantity(), book.get(), user.get());
                cartRepo.save(newCart);
                log.info("Cart record inserted successfully");
                //book.get().setQuantity(book.get().getQuantity() - cartdto.getQuantity());
                bookRepo.save(book.get());
                return newCart;
            } else {
                throw new BookStoreException("Requested quantity is not available");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    @Override
    public List<CartModel> getAllCartRecords() {
        List<CartModel> cartList = cartRepo.findAll();
        log.info("All cart records retrieved successfully");
        return cartList;
    }

    @Override
    public CartModel getCartRecord(Integer id) {
        Optional<CartModel> cart = cartRepo.findById(id);
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            log.info("Cart record retrieved successfully for id " + id);
            return cart.get();
        }
    }

    @Override
    public CartModel updateCartRecord(Integer id, CartDTO cartdto) {
        Optional<CartModel> cart = cartRepo.findById(id);
        Optional<BookModel> book = bookRepo.findById(cartdto.getBookID());
        Optional<UserModel> user = userRepo.findById(cartdto.getUserID());
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            if (book.isPresent() && user.isPresent()) {
                if (cartdto.getQuantity() <= book.get().getQuantity()) {
                    CartModel newCart = new CartModel(id, cartdto.getQuantity(), book.get(), user.get());
                    cartRepo.save(newCart);
                    log.info("Cart record updated successfully for id " + id);
                    //book.get().setQuantity(book.get().getQuantity() -(dto.getQuantity() -cart.get().getQuantity()));
                    bookRepo.save(book.get());
                    return newCart;
                } else {
                    throw new BookStoreException("Requested quantity is not available");
                }
            } else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        }
    }

    @Override
    public CartModel deleteCartRecord(Integer id) {
        Optional<CartModel> cart = cartRepo.findById(id);
        Optional<BookModel> book = bookRepo.findById(cart.get().getBook().getBookID());
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            //book.get().setQuantity(book.get().getQuantity() + cart.get().getQuantity());
            bookRepo.save(book.get());
            cartRepo.deleteById(id);
            log.info("Cart record deleted successfully for id " + id);
            return cart.get();
        }
    }

    @Override
    public List<CartModel> deleteAllFromCart() {
        cartRepo.deleteAll();
        return cartRepo.findAll();
    }
}
