package com.example.BookStore.controller;

import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.dto.ResponseDTO;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.service.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookservice")
@CrossOrigin
public class BookController {
    @Autowired
    private IBook service;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO bookdto) {
        ResponseDTO dto = new ResponseDTO("Book registered successfully !", service.insertBook(bookdto));
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @GetMapping("/retrieveAllBooks")
    public ResponseEntity<ResponseDTO> getAllBookRecords() {
        List<BookModel> newBook = service.getAllBookRecords();
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/retrieve/{bookName}")
    public ResponseEntity<ResponseDTO> getRecordByBookName(@PathVariable String bookName) {
        List<BookModel> newBook = service.getRecordByBookName(bookName);
        ResponseDTO dto = new ResponseDTO("Record for particular book retrieved successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id, @Valid @RequestBody BookDTO bookdto) {
        ResponseDTO dto = new ResponseDTO("Record updated successfully !", service.updateBookRecord(id, bookdto));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> deleteBookRecord(@PathVariable Integer id) {
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", service.deleteBookRecord(id));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
//    @GetMapping("/getBookById/{id}")
//    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
//        ResponseDTO dto = new ResponseDTO("Book Retrieved Successfully For Id => "+id,service.getBookRecord(id));
//        return new ResponseEntity<>(dto,HttpStatus.OK);
//    }
}
