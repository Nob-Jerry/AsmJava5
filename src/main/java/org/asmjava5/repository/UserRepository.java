package org.asmjava5.repository;

import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.fullname = :#{#user.fullname}, u.email = :#{#user.email}, u.phone = :#{#user.phone}, u.address = :#{#user.address}, u.role = :#{#user.role}, u.isActive = :#{#user.isActive} WHERE u.username = :#{#user.username}")
    Integer updateUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
    Integer updatePassword(@Param("password") String password, @Param("username") String username);
}
