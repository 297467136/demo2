package com.example.dao;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer>, JpaSpecificationExecutor {

    // HQL
    @Query("from Book b where b.bookname=:bookname")
    Book findBook(@Param("bookname") String bookname);

    Book findByBookname(String bookname);

    @Query("from Book b where b.id=:id")
    Book findById(@Param("id") String id);

    // SQL
    @Query(value = "select * from t_book b where b.bookname=?1 or b.bookname=?2", nativeQuery = true)
    List<Book> findBook1(@Param("bookname1") String bookname1, @Param("bookname2") String bookname2);

    @Transactional
    //@javax.transaction.Transactional
    @Modifying
    @Query(value = "delete from t_book where id = ?1", nativeQuery = true)
    void deleteById(String id);
}
