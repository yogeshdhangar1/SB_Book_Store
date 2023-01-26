package com.example.BookStore.model;

import com.example.BookStore.model.BookModel;
import com.example.BookStore.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderID;
    private LocalDate date = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    @OneToOne
    @JoinColumn(name="userID")
    private UserModel user;
    @OneToOne
    @JoinColumn(name="bookID")
    private BookModel book;
    private boolean cancel;
    public OrderModel(Integer orderID, Integer quantity, String address, BookModel book, UserModel user, boolean cancel) {
        this.orderID = orderID;
        this.price=quantity* book.getPrice();
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }
    public OrderModel( Integer quantity, String address, BookModel book, UserModel user, boolean cancel) {
        this.price=quantity* book.getPrice();
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }
}
