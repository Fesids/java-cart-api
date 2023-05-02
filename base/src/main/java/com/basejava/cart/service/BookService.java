package com.basejava.cart.service;

import com.basejava.cart.dto.BookRequest;
import com.basejava.cart.models.Book;

import java.util.List;

public interface BookService {

    Book createBook(BookRequest bookRequest);

    List<Book> getBooksByUserId(Long user_id);

    Book getBookById(Long book_id);

    Book updateBook(Long book_id, BookRequest bookRequest);

    void deleteBook(Long book_id);

    List<Book> getAllBooks();
}
