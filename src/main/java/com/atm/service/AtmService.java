package com.atm.service;

import com.atm.data.Currency;
import com.atm.repository.CurrencyRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AtmService {

    private final CurrencyRepo currencyRepo;

    public AtmService(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    @Transactional
    public Optional<Currency> getCurrencyById(Long id){
       return currencyRepo.findById(id);
    }
    @Transactional
    public void saveCurrency(Currency currency){
        currencyRepo.save(currency);
    }
    @Transactional
    public List<Currency> getAllCurrencies(){
        return (List<Currency>) currencyRepo.findAll();
    }


}
