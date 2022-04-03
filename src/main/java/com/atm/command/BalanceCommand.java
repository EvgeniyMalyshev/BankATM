package com.atm.command;

import com.atm.currencies.CurrencyHolder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BalanceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(BalanceCommand.class);
    private final CurrencyHolder currencyHolder;

    public BalanceCommand(CurrencyHolder currencyHolder) {
        this.currencyHolder = currencyHolder;
    }

    @Override
    public void execute() {
        currencyHolder.getBalance();
        logger.log(Level.INFO, "User asked of a balance");
    }
}
