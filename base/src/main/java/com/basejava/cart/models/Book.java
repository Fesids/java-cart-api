package com.basejava.cart.models;

import com.basejava.cart.dto.BookRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image", nullable = true, updatable = true)
    private String image;
    private String title;

    private String description;

    private BigDecimal price;

    private String author_book;

    //@ManyToMany(mappedBy = "books")
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private User book_user;


    //private Set<User> users = new HashSet<>();

    public Book(BookRequest bookRequest){
        this.title = bookRequest.getTitle();
        this.image = bookRequest.getImage();
        this.description = bookRequest.getDescription();
        this.price = bookRequest.getPrice();
        this.book_user =  bookRequest.getUser();
        this.author_book = bookRequest.getAuthor_book();
    }
}
