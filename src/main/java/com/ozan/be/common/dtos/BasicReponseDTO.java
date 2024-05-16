package com.ozan.be.common.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicReponseDTO implements Serializable {
  private Boolean data;
}
