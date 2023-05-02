package com.basejava.cart.service;

import com.basejava.cart.dto.BookRequest;
import com.basejava.cart.models.Book;
import com.basejava.cart.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book createBook(BookRequest bookRequest) {
        return bookRepository.save(new Book(bookRequest));
    }

    @Override
    public List<Book> getBooksByUserId(Long user_id) {
        return bookRepository.getBooksByUser(user_id);
    }

    @Override
    public Book getBookById(Long book_id) {
        return bookRepository.findById(book_id).get();
    }

    @Override
    public Book updateBook(Long book_id, BookRequest bookRequest) {

        var oldBook = bookRepository.findById(book_id).get();
        oldBook.setTitle(bookRequest.getTitle());
        oldBook.setDescription(bookRequest.getDescription());
        oldBook.setAuthor_book(bookRequest.getAuthor_book());
        oldBook.setPrice(bookRequest.getPrice());


        return bookRepository.save(oldBook);
    }

    @Override
    public void deleteBook(Long book_id) {
        bookRepository.deleteById(book_id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
