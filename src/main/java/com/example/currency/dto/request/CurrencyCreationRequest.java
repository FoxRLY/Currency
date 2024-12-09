package com.example.currency.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CurrencyCreationRequest {

  @NotNull
  String shortName;

  @NotNull
  String name;

}
