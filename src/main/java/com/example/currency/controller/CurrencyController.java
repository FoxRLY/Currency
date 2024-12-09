package com.example.currency.controller;

import com.example.currency.dto.request.CurrencyCreationRequest;
import com.example.currency.dto.request.CurrencyHistoryEntryCreationRequest;
import com.example.currency.dto.response.CurrencyHistoryResponse;
import com.example.currency.dto.response.CurrencyResponse;
import com.example.currency.mapper.CurrencyMapper;
import com.example.currency.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CurrencyController {

  private final CurrencyService currencyService;
  private final CurrencyMapper currencyMapper;

  @GetMapping("/list")
  public PagedModel<CurrencyResponse> getAllCurrencies(
      @PageableDefault(
          size = 20,
          sort = "id",
          direction = Sort.Direction.ASC
      )
      Pageable pageable) {
    return new PagedModel<>(currencyService.getAllCurrencies(pageable)
        .map(currencyMapper::toCurrencyResponse));
  }

  @GetMapping("/{shortName}/history")
  public PagedModel<CurrencyHistoryResponse> getCurrencyHistory(
      @PathVariable(name = "shortName") String shortName,
      @PageableDefault(
          size = 5,
          sort = "timestamp",
          direction = Sort.Direction.DESC
      ) Pageable pageable) {
    return new PagedModel<>(currencyService.getCurrencyHistory(shortName, pageable)
        .map(currencyMapper::toCurrencyHistoryResponse));
  }

  @PostMapping("/currency")
  public Long addNewCurrency(@Valid CurrencyCreationRequest request) {
    return currencyService.addNewCurrency(request);
  }

  @PostMapping("/currency/history")
  public void addNewCurrencyHistoryEntry(@Valid CurrencyHistoryEntryCreationRequest request) {
    currencyService.addNewCurrencyHistoryEntry(request);
  }

}
