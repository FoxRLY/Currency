package com.example.currency.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Value
@Builder(toBuilder = true)
public class CurrencyHistoryEntryCreationRequest {

  @NotNull
  Long currencyId;

  @NotNull
  BigDecimal price;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  LocalDate timestamp;

}
