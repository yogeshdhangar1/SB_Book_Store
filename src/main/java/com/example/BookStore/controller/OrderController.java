package com.example.BookStore.controller;

import com.example.BookStore.dto.OrderDTO;
import com.example.BookStore.dto.ResponseDTO;
import com.example.BookStore.service.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/orderservice")
public class OrderController {
@Autowired
    private IOrder service;
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto){
        ResponseDTO dto = new ResponseDTO("Order registered successfully !",service.insertOrder(orderdto));
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }
    @GetMapping("/retrieveAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrderRecords(){
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",service.getAllOrderRecords());
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @GetMapping("/retrieveOrder/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",service.getOrderRecord(id));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id,@Valid @RequestBody OrderDTO orderdto){
        ResponseDTO dto = new ResponseDTO("Record updated successfully !",service.updateOrderRecord(id,orderdto));
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderRecord(@PathVariable Integer id){
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !",service.deleteOrderRecord(id));
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
}
