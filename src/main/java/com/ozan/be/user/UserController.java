package com.ozan.be.user;

import com.ozan.be.user.domain.UserSearchFilter;
import com.ozan.be.user.dtos.UserResponseDTO;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  /*
   ONLY ADMIN USER CAN ACCESS ENDPOINTS BELOW
  */

  private final UserService service;

  @GetMapping()
  public ResponseEntity<Page<UserResponseDTO>> getAllUsers(
      @PageableDefault(size = 5) Pageable pageable,
      @ParameterObject UserSearchFilter searchFilter) {
    Page<UserResponseDTO> responseDTOS = service.getAllUsers(pageable, searchFilter.getPredicate());
    return ResponseEntity.ok(responseDTOS);
  }

  @GetMapping("/{id}")
  public void getUserDetails(@PathVariable("id") UUID id) {
    UserResponseDTO response = service.getUserDetails(id);
    ResponseEntity.ok(response);
  }
}
