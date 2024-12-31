package com.ecom.repository;

import com.ecom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.email = :email")
    Cart findCartByEmail(@Param("email") String email);

    @Query("SELECT c FROM Cart c WHERE c.user.email = :email AND c.cartId = :cartId")
    Cart findCartByEmailAndCartId(@Param("email") String emailId, @Param("cartId") Long cartId);
}
