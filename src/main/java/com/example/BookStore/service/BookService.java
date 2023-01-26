package com.example.BookStore.service;

import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.exception.BookStoreException;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.repository.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBook {
    @Autowired
    BookRepo bookRepo;

    @Override
    public BookModel insertBook(BookDTO bookdto) {
        BookModel newBook = new BookModel(bookdto);
        log.info("Book record inserted successfully");
        return bookRepo.save(newBook);
    }

    @Override
    public List<BookModel> getAllBookRecords() {
        List<BookModel> bookList = bookRepo.findAll();
        log.info("All book records retrieved successfully");
        return bookList;
    }

    @Override
    public List<BookModel> getRecordByBookName(String bookName) {
        List<BookModel> book = bookRepo.findByBookName(bookName);
        if (book.isEmpty()) {
            throw new BookStoreException("Book doesn't exists");
        } else {
            log.info("Book record retrieved successfully for Book Name : " + bookName);
            return book;
        }
    }

    @Override
    public BookModel updateBookRecord(Integer id, BookDTO bookdto) {
        Optional<BookModel> book = bookRepo.findById(id);
        if (book.isEmpty()) {
            throw new BookStoreException("Book Record doesn't exists");
        } else {
            BookModel newBook = new BookModel(id, bookdto);
            bookRepo.save(newBook);
            log.info("Book record updated successfully for id " + id);
            return newBook;
        }
    }

    @Override
    public BookModel deleteBookRecord(Integer id) {
        Optional<BookModel> book = bookRepo.findById(id);
        if (book.isEmpty()) {
            throw new BookStoreException("Book Record doesn't exists");
        } else {
            bookRepo.deleteById(id);
            log.info("Book record deleted successfully for id " + id);
            return book.get();
        }
    }

//    @Override
//    public List<BookModel> getBookRecord(Integer id) {
//        List<BookModel> book = bookRepo.findByBookId(id);
//        if (book.isEmpty()) {
//            throw new BookStoreException("Book Record Doesn't Exist");
//        } else {
//            log.info("Book record retrieved successfully for id " + id);
//            return book;
//        }
//    }

//    @Override
//    public List<BookModel> getBookRecord(Integer id) {
//        List<BookModel> book = bookRepo.findByBookId(id);
//        if (book.isEmpty()) {
//            throw new BookStoreException("Book Record doesn't exists");
//        } else {
//            log.info("Book record retrieved successfully for id " + id);
//            return book;
//        }
//    }
}
