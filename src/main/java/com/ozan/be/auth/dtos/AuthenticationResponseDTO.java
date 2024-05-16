package com.ozan.be.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ozan.be.user.domain.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("refresh_token")
  private String refreshToken;

  private UUID id;
  private String firstName;
  private String lastName;
  private String phone;
  private String email;

  private Instant createdAt;

  @Enumerated(EnumType.STRING)
  private Role role;
}
