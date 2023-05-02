package com.basejava.cart.controller;

import com.basejava.cart.dto.BookRequest;
import com.basejava.cart.models.Book;
import com.basejava.cart.service.BookService;
import com.basejava.cart.service.CartService;
import com.basejava.cart.service.UserService;
import com.basejava.cart.service.upload.FileStorageService;
import com.basejava.cart.utils.GeralUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/book")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    FileStorageService storageService;

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private CartService cartService;

    @GetMapping("/own_list_book/{user_id}")
    public ResponseEntity<Stream<Book>> getOwnBookList(@PathVariable("user_id") long user_id){
        var allBooksList = bookService.getAllBooks().stream()
                .filter(book -> book.getBook_user().getId() == user_id);
        return ResponseEntity.ok().body(allBooksList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody BookRequest bookRequest){
        return ResponseEntity.ok().body(bookService.updateBook(id, bookRequest));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @PostMapping(value = "/{user_id}/new", consumes = MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<Book> createBook(
            @PathVariable("user_id") Long user_id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") String price,
            @RequestParam("author_book") String author_book,
            @RequestParam("image") MultipartFile multipartFile

            )

    {


        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        BigDecimal res = new BigDecimal(price.replaceAll("\"", ""));

        var user = userService.getUserById(user_id);

        // test url using web server chrome extension
        var url = "http://127.0.0.1:8887/";
        var bookReq = BookRequest.builder()
                .title(title)
                .author_book(author_book)
                .description(description)
                .price(res)
                .image(url+fileName)
                .user(user).build();
        var newBook = bookService.createBook(bookReq);



        this.storageService.save(multipartFile);
        var URI = GeralUtilities.toUri("/new");

        return ResponseEntity.created(URI).body(newBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){


        var book = bookService.getBookById(id);
        var cart = cartService.getCart(book.getBook_user().getId());
        webClient.build().delete()
                .uri("http://localhost:8081/api/cart/remove_book_cart/"+cart.getCart_id()+"/"+book.getId())
                        .retrieve()
                                .bodyToMono(String.class)
                                        .block();
        bookService.deleteBook(id);

        return ResponseEntity.ok().body("the book with id "+id+" was deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @PostMapping(value = "/teste", consumes = MULTIPART_FORM_DATA_VALUE, headers = ("content-type=multipart/form-data"))
    public ResponseEntity<?> uploadfile(@RequestParam(value = "file") MultipartFile file){
        this.storageService.save(file);
        return ResponseEntity.ok().body("worked");

    }

    @GetMapping("/teste/{file_name}")
    public ResponseEntity<?> getFile(@PathVariable("file_name") String file_name) throws IOException {
        var fileImg = this.storageService.load(file_name).getFile();
        System.out.println(fileImg);
        return ResponseEntity.ok().body(fileImg);
    }



}
