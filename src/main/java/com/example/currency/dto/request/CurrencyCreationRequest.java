package com.example.currency.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CurrencyCreationRequest {

  @NotBlank(message = "shortName cannot be blank")
  @NotNull(message = "shortName cannot be null")
  String shortName;

  @NotBlank(message = "name cannot be blank")
  @NotNull(message = "name cannot be null")
  String name;

}
