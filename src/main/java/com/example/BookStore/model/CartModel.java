package com.example.BookStore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer cartID;
    @OneToOne()
    @JoinColumn(name="userID")
    private UserModel user;
    @ManyToOne()
    @JoinColumn(name="bookID")
    private BookModel book;
    private Integer quantity;

    private Integer totalPrice;
    public CartModel(Integer cartID,Integer quantity, BookModel book, UserModel user) {
        this.cartID= cartID;
        this.quantity = quantity;
        this.book=book;
        this.user=user;
        this.totalPrice=quantity*book.getPrice();
    }
    public CartModel(Integer quantity, BookModel book, UserModel user) {
        this.quantity = quantity;
        this.book=book;
        this.user=user;
        this.totalPrice=quantity*book.getPrice();
    }

}
