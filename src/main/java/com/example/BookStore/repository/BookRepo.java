package com.example.BookStore.repository;

import com.example.BookStore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookModel,Integer> {
    @Query(value="select * from book_model where book_name LIKE :bookName%",nativeQuery=true)
     List<BookModel> findByBookName(String bookName);

//    List<BookModel> findByBookId(Integer id);
}
