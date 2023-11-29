package com.ozan.be.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ozan.be.user.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;

  private Integer selfID;
  private String firstName;
  private String lastName;
  private String phone;
  private String email;
  @Column(columnDefinition = "timestamp")
  private LocalDateTime createdAt;
  @Enumerated(EnumType.STRING)
  private Role role;
}
