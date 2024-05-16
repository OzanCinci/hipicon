package com.ozan.be.customException.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonError {
  private int status;
  private String source;
  private String code;
  private String detail;

  public static CommonError fromFieldError(FieldError err) {
    return new CommonError(422, err.getField(), err.getCode(), err.getDefaultMessage());
  }
}
