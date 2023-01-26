package com.example.BookStore.service;

import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.model.BookModel;

import java.util.List;

public interface IBook {
    public BookModel insertBook(BookDTO bookdto);

    List<BookModel> getAllBookRecords();

    List<BookModel> getRecordByBookName(String bookName);

    public BookModel updateBookRecord(Integer id, BookDTO bookdto);

    public BookModel deleteBookRecord(Integer id);
//    public List<BookModel> getBookRecord(Integer id);

}
