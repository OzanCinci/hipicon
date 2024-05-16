package com.ozan.be.token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(
      value =
          "SELECT t.* "
              + "FROM token t "
              + "JOIN _user u ON t.user_id = u.id "
              + "WHERE u.id = :id AND (t.expired = false OR t.revoked = false)",
      nativeQuery = true)
  List<Token> findAllValidTokenByUser(@Param("id") UUID id);

  Optional<Token> findByToken(String token);
}
