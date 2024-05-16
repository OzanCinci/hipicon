package com.ozan.be.auth.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshTokenRequestDTO {
  @NotNull @NotEmpty private String refreshToken;
}
