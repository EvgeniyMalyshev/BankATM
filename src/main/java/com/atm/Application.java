package com.atm;

import com.atm.command.ScanCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final ScanCommand scanCommand;

    public Application(ScanCommand scanCommand) {
        this.scanCommand = scanCommand;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        scanCommand.execute();
    }
}