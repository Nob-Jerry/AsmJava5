package org.asmjava5.repository;

import org.asmjava5.data.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_User_Username(String userName);
    void deleteByCart_User_UserIdAndProduct_ProductId(Long userId, Long productId);
    void deleteByCart_User_UserIdAndProduct_ProductIdIn(Long userId, List<Long> productIds);
}
