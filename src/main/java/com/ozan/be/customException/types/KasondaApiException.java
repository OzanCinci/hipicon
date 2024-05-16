package com.ozan.be.customException.types;

public class KasondaApiException extends RuntimeException {
  public KasondaApiException(String message) {
    super(message);
  }
}
