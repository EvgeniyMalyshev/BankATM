package com.atm.service;

import com.atm.data.Currency;
import com.atm.repository.CurrencyRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AtmService {

    private final CurrencyRepo currencyRepo;

    public AtmService(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    @Transactional
    Optional<Currency> getCurrencyById(Long id){
       return currencyRepo.findById(id);
    }
}
