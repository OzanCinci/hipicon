package com.ozan.be.user;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @PatchMapping
  public ResponseEntity<?> changePassword(
      @RequestBody ChangePasswordRequest request, Principal connectedUser) {
    service.changePassword(request, connectedUser);
    return ResponseEntity.ok().build();
  }

  @GetMapping("getUserDetails/{userID}")
  public User getUserDetails(@PathVariable Integer userID) {
    return service.getUserDetails(userID);
  }
}
