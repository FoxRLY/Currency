package com.example.currency.config;

import com.example.currency.dto.response.BindErrorResponse;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BindException.class)
  public ResponseEntity<BindErrorResponse> handleBindException(BindException ex) {
    log.error("", ex);

    BindingResult bindingResult = ex.getBindingResult();

    if (bindingResult.hasErrors()) {
      final List<BindErrorResponse.FieldError> errorList = bindingResult.getFieldErrors()
          .stream()
          .map(fieldError -> new BindErrorResponse.FieldError(
              fieldError.getField(),
              fieldError.getRejectedValue().toString(),
              fieldError.getDefaultMessage()))
          .toList();

      BindErrorResponse response = BindErrorResponse.builder()
          .errorCount((long) bindingResult.getErrorCount())
          .errors(errorList)
          .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BindErrorResponse.builder().errorCount(0L).build());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<String> handleDataIntegrityException(DataIntegrityViolationException ex) {
    log.error("", ex);
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Database error");
  }

  @ExceptionHandler({DataAccessException.class, SQLException.class})
  public ResponseEntity<String> handleDatabaseException(Exception ex) {
    log.error("", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    log.error("", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
  }

}
