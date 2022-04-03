package com.atm.currencies;

import com.atm.command.ScanCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CurrencyHolder {
    private static final Map<String, Map<String, Integer>> numbersOfBanknotes = new HashMap<>();
    private static final Map<String, Integer> cashFlow = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(CurrencyHolder.class);

    public String getCashFromHolder(String currencyName, String summ) {
        if (!numbersOfBanknotes.containsKey(currencyName)) {
            newCurrenciesHolder(currencyName);
        }
        int tempSum = Integer.parseInt(summ);
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        Map<String, Integer> currencyMap = numbersOfBanknotes.get(currencyName);
        Set<String> values = currencyMap.keySet();
        StringBuilder answer = new StringBuilder();
        for (String value : values) {
            int tempCount = 0;
            int countOfBanknotes = currencyMap.get(value);
            for (int i = 0; i < countOfBanknotes; i++) {
                if (tempSum != 0 && (tempSum - Integer.parseInt(value) >= 0)) {
                    tempSum = tempSum - Integer.parseInt(value);
                    tempCount++;
                    tempMap.put(value, tempCount);
                }
            }
        }
        if (tempSum == 0) {
            for (Map.Entry entry : tempMap.entrySet()) {
                answer.append(entry.getKey().toString()).append(" ").append(entry.getValue().toString());
                currencyMap.put(entry.getKey().toString(), currencyMap.get(entry.getKey().toString()) - Integer.parseInt(entry.getValue().toString()));
            }
            numbersOfBanknotes.put(currencyName, currencyMap);
            return answer.toString();
        }
        logger.log(Level.ERROR, "Not enough banknotes to withdraw");
        return "ERROR";
    }


    public void putCashIntoHolder(String currencyName, String banknotesValue, Integer banknotesAmount) {
        currencyCheck(currencyName, numbersOfBanknotes);
        setSumm(currencyName, banknotesValue, banknotesAmount);
        numbersOfBanknotes.put(currencyName, setBanknotes(numbersOfBanknotes.get(currencyName), banknotesValue, banknotesAmount));
        System.out.println("OK");
    }

    public void getBalance() {
        numbersOfBanknotes.forEach((key, value) ->
                value.forEach((keyInner, valueInner) -> {
                    if (valueInner != 0) {
                        System.out.println(key + " " + keyInner + " " + valueInner);
                    }
                }));
    }

    private void setSumm(String currencyName, String banknotesValue, Integer banknotesAmount) {
        if (!cashFlow.containsKey(currencyName)) {
            cashFlow.put(currencyName, 0);
        }
        cashFlow.put(currencyName, cashFlow.get(currencyName) + (Integer.parseInt(banknotesValue) * banknotesAmount));
    }

    private Map<String, Integer> setBanknotes(Map<String, Integer> amountOfBanknotes, String banknotesValue, Integer banknotesAmount) {
        amountOfBanknotes.put(banknotesValue, (amountOfBanknotes.get(banknotesValue) + banknotesAmount));
        return amountOfBanknotes;
    }

    private void currencyCheck(String currencyName, Map<String, Map<String, Integer>> numbersOfBanknotes) {
        if (!numbersOfBanknotes.containsKey(currencyName)) {
            numbersOfBanknotes.put(currencyName, newBanknotesHolder());
        }
    }

    private Map<String, Integer> newBanknotesHolder() {
        Map<String, Integer> newHolder = new LinkedHashMap<>();
        newHolder.put("5000", 0);
        newHolder.put("1000", 0);
        newHolder.put("500", 0);
        newHolder.put("100", 0);
        newHolder.put("50", 0);
        newHolder.put("10", 0);
        return newHolder;
    }


    private void newCurrenciesHolder(String currency) {
        numbersOfBanknotes.put(currency, newBanknotesHolder());
    }
}
