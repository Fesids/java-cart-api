package com.basejava.cart.service;

import com.basejava.cart.dto.CartRequest;
import com.basejava.cart.models.Cart;
import com.basejava.cart.repositories.BookRepository;
import com.basejava.cart.repositories.CartRepository;
import com.basejava.cart.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService{



    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Cart createCart(long user_id) {
        var user = userRepository.findById(user_id).get();
        var cart_user = new CartRequest(user);

        return cartRepository.save(new Cart(cart_user));
    }

    @Override
    public Cart getCart(long user_id) {

        return cartRepository.findCartByUserId(user_id).get();
    }

    @Override
    public void AddBookToCart(long user_id, long book_id) {

        /*var oldBook = bookRepository.findById(book_id).get();
        oldBook.setTitle(bookRequest.getTitle());
        oldBook.setDescription(bookRequest.getDescription());
        oldBook.setAuthor_book(bookRequest.getAuthor_book());
        oldBook.setPrice(bookRequest.getPrice());


        return bookRepository.save(oldBook);*/

        var cart = cartRepository.findCartByUserId(user_id).get();
        /*var user = userRepository.findById(user_id).get();
        cart.setUser(user);*/
        /*var booklist = new ArrayList<Book>();
        booklist.add(book);
        cart.setCart_books(booklist);
        cartRepository.save(cart);*/
        /*var book = bookRepository.findById(book_id).get();
        cart.getCart_books().add(book);*/
        var bookToAdd = bookRepository.findById(book_id);
        if(!cart.getCart_books().contains(bookToAdd.get())){
            cart.getCart_books().add(bookToAdd.get());
        }

        cartRepository.save(cart);
    }

    @Override
    public void DeleteBookFromCart(long user_id, long book_id) {
        var cart = cartRepository.findCartByUserId(user_id).get();
        var book = bookRepository.findById(book_id);

        cart.getCart_books().remove(book.get());
        cartRepository.save(cart);
    }
}
