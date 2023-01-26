package com.example.BookStore.controller;

import com.example.BookStore.dto.CartDTO;
import com.example.BookStore.dto.ResponseDTO;
import com.example.BookStore.model.CartModel;
import com.example.BookStore.service.ICart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cartservice")
@CrossOrigin
public class CartController {
 @Autowired
 private ICart service;
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody CartDTO cartdto){
        ResponseDTO dto = new ResponseDTO("Book Added To Cart successfully !",service.insertCart(cartdto));
        System.out.println("Cart record updated successfully  "+ dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("/retrieveAllCarts")
    public ResponseEntity<ResponseDTO> getAllCartRecords(){
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",service.getAllCartRecords());
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @GetMapping("/retrieveCart/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",service.getCartRecord(id));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @PutMapping("/updateCart/{id}")
    public ResponseEntity<ResponseDTO> updateCartRecord(@PathVariable Integer id,@Valid @RequestBody CartDTO cartdto){
        ResponseDTO dto = new ResponseDTO("Record updated successfully !",service.updateCartRecord(id,cartdto));
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<ResponseDTO> deleteCartRecord(@PathVariable Integer id) {
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", service.deleteCartRecord(id));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteall")
    public ResponseEntity<ResponseDTO> deleteBooks() {
        List<CartModel> books = service.deleteAllFromCart();
        return new ResponseEntity(books, HttpStatus.OK);
    }

}
