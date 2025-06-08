package org.asmjava5.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "NAME")
    private String productName;

    @Column(name = "PRICE")
    private Double productPrice;

    @Column(name = "QUANTITY")
    private Integer productQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "IS_HOT")
    private Boolean isHot;

    @Column(name = "IS_DISCOUNT")
    private Boolean isDiscount;

    @Column(name = "IS_NEW")
    private Boolean isNew;

    @Column(name = "DISCOUNT_PERCENT")
    private Double discountPercent;

    @Column(name = "RATING")
    private Double rating;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}

