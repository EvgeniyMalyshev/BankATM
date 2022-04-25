package com.atm.command;

import com.atm.currencies.CurrencyHolder;
import com.atm.util.CheckService;
import com.atm.util.UserScanner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class OutputCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OutputCommand.class);
    private final CurrencyHolder currencyHolder;
    private final CheckService checkService;
    private final UserScanner userScanner;

    public OutputCommand(CurrencyHolder currencyHolder, CheckService checkService, UserScanner userScanner) {
        this.currencyHolder = currencyHolder;
        this.checkService = checkService;
        this.userScanner = userScanner;
    }

    public void execute() {
        String inputCurrency = userScanner.getScanner().next();
        String inputValue = userScanner.getScanner().next();
        if (checkService.currencyCheck(inputCurrency)
                && checkService.valueCheck(inputValue)) {
            String withdraw = currencyHolder.getCashFromHolder(inputCurrency, inputValue);
            System.out.println(withdraw);
            logger.log(Level.INFO, "User withdraw " + withdraw);
        } else {
            System.out.println("ERROR");
            logger.log(Level.ERROR, "Not enough money to withdraw");

        }

    }
}
