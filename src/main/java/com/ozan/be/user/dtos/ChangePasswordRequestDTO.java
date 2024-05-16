package com.ozan.be.user.dtos;

import java.io.Serializable;
import lombok.Data;

@Data
public class ChangePasswordRequestDTO implements Serializable {
  private String currentPassword;
  private String newPassword;
  private String confirmationPassword;
}
