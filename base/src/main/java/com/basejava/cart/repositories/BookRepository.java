package com.basejava.cart.repositories;

import com.basejava.cart.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from books where user_id = :user_id", nativeQuery = true)
    List<Book> getBooksByUser(@Param("user_id") Long user_id);

}
