package com.easytobuy.exception;

import com.easytobuy.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ProductCustomException.class)
  public ResponseEntity<ErrorResponse> handleCategoryIdException(
      ProductCustomException productCustomException) {
    return new ResponseEntity<>(
        new ErrorResponse()
            .builder()
            .errorCode(productCustomException.getErrorCode())
            .errorMessage(productCustomException.getMessage())
            .build(),
        HttpStatus.NOT_FOUND);
  }
}
