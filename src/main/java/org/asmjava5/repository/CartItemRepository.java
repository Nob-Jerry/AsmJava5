package org.asmjava5.repository;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.request.update.CartItemUpdateRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart_User_Username(String userName);

    void deleteByCart_User_UserIdAndProduct_ProductIdIn(Long userId, List<Long> productIds);
    CartItem findByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CartItem ci SET ci.quantity = :#{#cartItemUpdateRequest.quantity} WHERE ci.cartItemId = :#{#cartItemUpdateRequest.cartItemId} AND ci.product.productId = :#{#cartItemUpdateRequest.productId}")
    Integer updateCartItem(@Param("cartItemUpdateRequest") CartItemUpdateRequest cartItemUpdateRequest);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
            value = "INSERT INTO CARTITEM (CART_ID, PRODUCT_ID, QUANTITY) VALUES (:cartId, :productId, :quantity)",
            nativeQuery = true
    )
    Integer createCartItem(@Param("cartId") Long cartId,
                           @Param("productId") Long productId,
                           @Param("quantity") Integer quantity
    );
}
