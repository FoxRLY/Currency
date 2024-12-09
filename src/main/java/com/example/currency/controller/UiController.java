package com.example.currency.controller;

import com.example.currency.dto.response.CurrencyHistoryResponse;
import com.example.currency.dto.response.CurrencyResponse;
import com.example.currency.mapper.CurrencyMapper;
import com.example.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UiController {

  private final CurrencyService currencyService;
  private final CurrencyMapper currencyMapper;

  @GetMapping
  public String mainPage(Model model) {
    final Page<CurrencyResponse> currencies = currencyService
        .getAllCurrencies(PageRequest.of(0, 1000,
            Sort.by(Sort.Order.asc("id"))))
        .map(currencyMapper::toCurrencyResponse);

    model.addAttribute("currencyList", currencies);

    return "main_page";
  }

  @GetMapping("/{shortName}/history")
  public String historyPage(@PathVariable("shortName") String shortName, Model model) {
    final Page<CurrencyHistoryResponse> history = currencyService
        .getCurrencyHistory(shortName, PageRequest.of(0, 1000,
            Sort.by(Sort.Order.desc("timestamp"))))
        .map(currencyMapper::toCurrencyHistoryResponse);

    model.addAttribute("currencyHistory", history);

    return "history_page";
  }

}
