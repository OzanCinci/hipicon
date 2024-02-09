package com.ozan.be.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Optional<User> findUserById(Integer id);

  @Query(
      "SELECT u FROM User u WHERE LOWER(u.email) LIKE %:searchTerm% OR LOWER(u.firstname) LIKE %:searchTerm% OR LOWER(u.lastname) LIKE %:searchTerm%")
  List<User> findUsersByEmailNameOrSurnameContainingIgnoreCase(String searchTerm);

  List<User> findUsersByRoleIsNot(Role role);
}
