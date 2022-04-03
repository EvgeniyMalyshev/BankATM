package com.atm.util;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserScanner {
    Scanner scanner = new Scanner(System.in);

    public Scanner getScanner() {
        return scanner;
    }

}
