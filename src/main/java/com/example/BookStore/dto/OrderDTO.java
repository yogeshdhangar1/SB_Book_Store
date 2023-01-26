package com.example.BookStore.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDTO {
    private Integer quantity;
    @NotEmpty(message="Please provide address")
    private String address;
    private Integer userID;

    private Integer bookID;
    private boolean cancel;
    private  Integer cartID;
}
