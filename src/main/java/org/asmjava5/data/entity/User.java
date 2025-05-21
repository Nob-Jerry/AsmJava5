package org.asmjava5.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERACCOUNT")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

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
    private String createdAt;
}
