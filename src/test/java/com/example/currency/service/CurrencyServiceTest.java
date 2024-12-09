package com.example.currency.service;

import com.example.currency.entity.Currency;
import com.example.currency.entity.CurrencyHistory;
import com.example.currency.repository.CurrencyHistoryRepository;
import com.example.currency.repository.CurrencyRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

  @Mock
  private CurrencyRepository currencyRepository;

  @Mock
  private CurrencyHistoryRepository currencyHistoryRepository;

  @InjectMocks
  private CurrencyService currencyService;

  @BeforeEach
  void setUp() {
  }

  @Test
  void getCurrencyHistory() {
    Mockito.when(currencyRepository.findByShortName("USD"))
        .thenReturn(new Currency().setId(1L).setShortName("USD").setName("Доллар"));
    Mockito.when(currencyHistoryRepository.getByCurrencyId(1L,
            PageRequest.of(0, 1)))
        .thenReturn(new PageImpl<>(Collections.singletonList(new CurrencyHistory()
            .setCurrencyId(1L)
            .setId(1L)
            .setPrice(BigDecimal.valueOf(32.456))
            .setTimestamp(LocalDate.of(2024, 12, 12))
        )));

    Page<CurrencyHistory> history = currencyService.getCurrencyHistory("USD",
        PageRequest.of(0, 1));

    assertIterableEquals(
        currencyHistoryRepository.getByCurrencyId(1L,
            PageRequest.of(0, 1)).getContent(),
        history.getContent()
        );

  }

}