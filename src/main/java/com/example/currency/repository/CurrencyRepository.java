package com.example.currency.repository;

import com.example.currency.entity.Currency;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

  List<Currency> findAll();

  Page<Currency> findAll(Pageable pageable);

  Currency findByShortName(String shortName);

}
