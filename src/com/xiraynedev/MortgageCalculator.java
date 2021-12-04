package com.xiraynedev;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class MortgageCalculator {
    Scanner scanner = new Scanner(System.in);
    private int principal;
    private float annualInterestRate;
    private byte loanPeriod;
    private double mortgage;

    public MortgageCalculator() {
        getPrincipal();
        getAnnualInterestRate();
        getLoanPeriod();
        calculateMortgage();
        formatAndPrintMortgage(mortgage);
    }

    private void getPrincipal() {
        principal = (int) (getUserInput("Principal ($1K - $1M): ", 1000, 1_000_000));
    }

    private void getAnnualInterestRate() {
        annualInterestRate = (float) (getUserInput("Annual Interest Rate (1.0 - 30.0): ", 0, 30));
    }

    private void getLoanPeriod() {
        loanPeriod = (byte) (getUserInput("Loan Period (Years) 1 - 30: ", 0, 30));
    }

    private double getUserInput(String prompt, double min, double max) {
        double inputValue;
        while (true) {
            System.out.print(prompt);
            inputValue = scanner.nextDouble();
            if (inputValue >= min && inputValue <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return inputValue;
    }

    private void calculateMortgage() {
        final byte PERCENT = 100;
        final byte MONTHS_IN_YEAR = 12;
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
        short numberOfPayments = (short) (loanPeriod * MONTHS_IN_YEAR);

        mortgage = principal
                * (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1));
    }

    private void formatAndPrintMortgage(double mortgage) {
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(mortgage));
    }
}
