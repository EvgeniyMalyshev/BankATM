package com.atm.util;

import com.atm.command.ScanCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

@Service
public class CheckService {
    private static final Logger logger = LogManager.getLogger(CheckService.class);

    public boolean directivesCheck(String inputDirection) {
        logger.log(Level.INFO, "Input directions is " + inputDirection);
        return inputDirection.equals("-") || inputDirection.equals("+") || inputDirection.equals("?");
    }

    public boolean currencyCheck(String inputCurrency) {
        logger.log(Level.INFO, "Input currency is " + inputCurrency);
        String regex = "([A-Z]{3})";
        return Pattern.matches(regex, inputCurrency);
    }

    public boolean valueCheck(String inputValue) {
        logger.log(Level.INFO, "Input value is " + inputValue);
        return numberCheck(inputValue) && banknotesCheck(Integer.parseInt(inputValue));
    }

    public boolean banknotesCheck(int banknotesValue) {
        logger.log(Level.INFO, "Input banknotes value is " + banknotesValue);
        Set<Integer> banknotesValues = new TreeSet<>(Arrays.asList(10, 100, 1000, 50, 500, 5000));
        return banknotesValues.contains(banknotesValue);
    }

    public boolean numberCheck(String numberValue) {
        logger.log(Level.INFO, "Input number is " + numberValue);
        String regex = "^[1-9]\\d*$";
        return Pattern.matches(regex, numberValue);
    }
}
