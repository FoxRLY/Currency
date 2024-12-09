package com.example.currency.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.currency.entity.Currency;
import com.example.currency.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

  @Mock
  private CurrencyRepository currencyRepository;

  @InjectMocks
  private CurrencyService currencyService;

  @BeforeEach
  void setUp() {
  }

  @Test
  void getCurrencyByShortName() {
    Mockito.when(currencyRepository.findByShortName("USD"))
        .thenReturn(new Currency().setName("Доллар").setShortName("USD"));
    assertEquals(currencyService.getCurrencyByShortName("USD").getName(), "Доллар");
  }
}