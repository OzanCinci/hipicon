package com.ozan.be.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
      throws Exception {
    try {
      return ResponseEntity.ok(service.register(request));
    } catch (Exception e) {
      if (e.getMessage().contains(" already exists")) {
        throw new RuntimeException(
            "User with email address: " + request.getEmail() + " already exists");
      }
      throw new RuntimeException(
          "Something went wrong, in case of recurrence please contact us");
    }
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    try {
      return ResponseEntity.ok(service.authenticate(request));
    } catch (Exception e) {
      if (e.getMessage().contains("Bad credentials")) {
        throw new RuntimeException(
            "User with email address: " + request.getEmail() + " doesn't exists");
      }
      throw new RuntimeException(
          "Something went wrong, in case of recurrence please contact us");
    }
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    service.refreshToken(request, response);
  }
}
