package org.asmjava5.repository;

import org.asmjava5.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByUser_UserId(@Param("userId") Long userId);
}
