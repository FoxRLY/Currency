package com.example.currency.mapper;

import com.example.currency.dto.request.CurrencyCreationRequest;
import com.example.currency.dto.request.CurrencyHistoryEntryCreationRequest;
import com.example.currency.dto.response.CurrencyHistoryResponse;
import com.example.currency.dto.response.CurrencyResponse;
import com.example.currency.entity.Currency;
import com.example.currency.entity.CurrencyHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyMapper {

  CurrencyResponse toCurrencyResponse(Currency currency);

  CurrencyHistoryResponse toCurrencyHistoryResponse(CurrencyHistory history);

  Currency toCurrencyModel(CurrencyCreationRequest request);

  CurrencyHistory toCurrencyHistoryModel(CurrencyHistoryEntryCreationRequest request);
}
