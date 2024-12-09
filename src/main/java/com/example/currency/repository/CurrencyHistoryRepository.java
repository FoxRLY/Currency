package com.example.currency.repository;

import com.example.currency.entity.CurrencyHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyHistoryRepository extends CrudRepository<CurrencyHistory, Long> {

  Page<CurrencyHistory> getByCurrencyId(Long currencyId, Pageable pageable);

}
