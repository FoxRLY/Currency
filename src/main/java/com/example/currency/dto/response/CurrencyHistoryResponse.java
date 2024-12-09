package com.example.currency.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CurrencyHistoryResponse {

  BigDecimal price;

  LocalDate timestamp;

}
