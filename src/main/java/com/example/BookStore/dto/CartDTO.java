package com.example.BookStore.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartDTO {
    private Integer userID;
    private Integer bookID;
    @NotNull(message="Book quantity yet to be provided")
    private Integer quantity;
}
