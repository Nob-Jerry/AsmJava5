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
@Table(name = "USERACCOUNT")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FULL_NAME")
    private String fullname;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "ACTIVATION_TOKEN")
    private String activationToken;

    @Column(name = "RESET_TOKEN")
    private String resetToken;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "IS_VERIFIED")
    private Boolean isVerified;

    @Column(name = "IS_GOOGLE_USER")
    private Boolean isGoogleUser;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
