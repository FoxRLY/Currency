package com.example.currency.service;

import com.example.currency.dto.request.CurrencyCreationRequest;
import com.example.currency.dto.request.CurrencyHistoryEntryCreationRequest;
import com.example.currency.entity.Currency;
import com.example.currency.entity.CurrencyHistory;
import com.example.currency.mapper.CurrencyMapper;
import com.example.currency.repository.CurrencyHistoryRepository;
import com.example.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {

  private final CurrencyRepository currencyRepository;
  private final CurrencyHistoryRepository currencyHistoryRepository;
  private final CurrencyMapper currencyMapper;

  public Page<Currency> getAllCurrencies(Pageable pageable) {
    return currencyRepository.findAll(pageable);
  }

  public Page<CurrencyHistory> getCurrencyHistory(String shortName, Pageable pageable) {
    final Long currencyId = currencyRepository.findByShortName(shortName).getId();
    return currencyHistoryRepository.getByCurrencyId(currencyId, pageable);
  }

  public Long addNewCurrency(CurrencyCreationRequest request) {
    Currency newCurrency = currencyMapper.toCurrencyModel(request);
    currencyRepository.save(newCurrency);
    return newCurrency.getId();
  }

  public Long addNewCurrencyHistoryEntry(CurrencyHistoryEntryCreationRequest request) {
    CurrencyHistory newEntry = currencyMapper.toCurrencyHistoryModel(request);
    currencyHistoryRepository.save(newEntry);
    return newEntry.getId();
  }
}
