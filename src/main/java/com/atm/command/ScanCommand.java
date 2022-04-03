package com.atm.command;

import com.atm.util.CheckService;
import com.atm.util.UserScanner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class ScanCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ScanCommand.class);

    private final InputCommand inputCommand;
    private final OutputCommand outputCommand;
    private final BalanceCommand balanceCommand;
    private final CheckService checkService;
    private final UserScanner userScanner;

    public ScanCommand(InputCommand inputCommand, OutputCommand outputCommand, BalanceCommand balanceCommand, CheckService checkService, UserScanner userScanner) {
        this.inputCommand = inputCommand;
        this.outputCommand = outputCommand;
        this.balanceCommand = balanceCommand;
        this.checkService = checkService;
        this.userScanner = userScanner;
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("Insert command ");
            logger.log(Level.INFO, "ScanCommand : Operation start");
            String inputDirection = userScanner.getScanner().next();
            if (!checkService.directivesCheck(inputDirection)) {
                logger.log(Level.ERROR, "Wrong symbols " + inputDirection);
                System.out.println("ERROR");
            }
            {
                if (inputDirection.equals("?")) {
                    balanceCommand.execute();
                } else if (inputDirection.equals("+")) {
                    inputCommand.execute();
                } else {
                    outputCommand.execute();
                }
            }
        }
    }








}
