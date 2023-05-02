package com.basejava.cart.repositories;

import com.basejava.cart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select * from user_cart where user_id = :user_id", nativeQuery = true)
    Optional<Cart> findCartByUserId(@Param("user_id") long user_id);
}
