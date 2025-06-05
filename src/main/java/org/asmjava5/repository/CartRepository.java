package org.asmjava5.repository;

import org.asmjava5.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("""
                SELECT c FROM Cart c
                JOIN FETCH c.user u
                JOIN FETCH c.cartItems ci
                JOIN FETCH ci.product
                WHERE u.userId = :userId
            """)
    Cart findCartByUser_UserId(@Param("userId") Long userId);
}
