package com.atm.repository;

import com.atm.data.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends CrudRepository<Currency, Long> {
}
