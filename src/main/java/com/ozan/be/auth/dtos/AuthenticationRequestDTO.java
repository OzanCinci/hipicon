package com.ozan.be.auth.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {
  @NotNull private String email;
  @NotNull private String password;
}
