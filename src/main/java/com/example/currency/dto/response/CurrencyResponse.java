package com.example.currency.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CurrencyResponse {

  String name;

  String shortName;

}
