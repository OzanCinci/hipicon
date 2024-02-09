package com.ozan.be.customException;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

// private final, getter, public constructor, equals, hashcode
// and to string
public record ApiException(
    String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {}
