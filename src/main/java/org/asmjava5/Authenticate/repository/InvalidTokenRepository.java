package org.asmjava5.Authenticate.repository;

import org.asmjava5.Authenticate.data.entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
    Boolean existsInvalidTokenByInvalidTokenId(String invalidTokenId);
}
