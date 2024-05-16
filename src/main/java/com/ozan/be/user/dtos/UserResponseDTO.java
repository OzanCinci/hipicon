package com.ozan.be.user.dtos;

import com.ozan.be.user.domain.Role;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class UserResponseDTO implements Serializable {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private Role role;
  private Instant createdAt;
}
