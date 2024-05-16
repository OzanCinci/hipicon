package com.ozan.be.auth.dtos;

import com.ozan.be.user.domain.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDTO {
  @NotNull private String firstName;

  @NotNull private String lastName;

  @NotNull
  @Email(message = "Email should be valid")
  private String email;

  @NotNull private String password;

  @NotNull private String phone;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;
}
