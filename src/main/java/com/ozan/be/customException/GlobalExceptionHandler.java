package com.ozan.be.customException;

import com.ozan.be.customException.domain.CommonError;
import com.ozan.be.customException.domain.CommonErrorResponse;
import com.ozan.be.customException.types.BadRequestException;
import com.ozan.be.customException.types.DataNotFoundException;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public CommonErrorResponse handleBadRequestException(BadRequestException exception) {

    log.error("Exception occurred BadRequestException : {}", exception.getMessage());
    return returnCommonErrorResponse(
        HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public CommonErrorResponse handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException exception) {

    log.error(
        "Exception occurred HttpRequestMethodNotSupportedException : {} ", exception.getMessage());
    return returnCommonErrorResponse(
        HttpStatus.METHOD_NOT_ALLOWED,
        HttpStatus.METHOD_NOT_ALLOWED.toString(),
        exception.getMessage());
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public CommonErrorResponse handleAuthenticationException(AuthenticationException exception) {
    log.error(
        "Exception occurred HttpRequestMethodNotSupportedException : {} ", exception.getMessage());
    return returnCommonErrorResponse(
        HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.toString(), exception.getMessage());
  }

  @ExceptionHandler(DataNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public CommonErrorResponse handleDataNotFoundException(DataNotFoundException exception) {
    log.error(
        "Exception occurred HttpRequestMethodNotSupportedException : {} ", exception.getMessage());
    return returnCommonErrorResponse(
        HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString(), exception.getMessage());
  }

  private CommonErrorResponse returnCommonErrorResponse(
      HttpStatus status, String code, String message) {
    CommonError commonError = new CommonError();
    commonError.setStatus(status.value());
    commonError.setDetail(message);
    commonError.setCode(code);

    CommonErrorResponse commonErrorResponse = new CommonErrorResponse();
    commonErrorResponse.setErrors(Collections.singletonList(commonError));

    return commonErrorResponse;
  }
}
