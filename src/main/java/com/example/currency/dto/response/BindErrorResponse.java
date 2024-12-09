package com.example.currency.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class BindErrorResponse {

  @Value
  public static class FieldError {
    String fieldName;
    String rejectedValue;
    String message;
  }

  Long errorCount;
  List<FieldError> errors;

}
