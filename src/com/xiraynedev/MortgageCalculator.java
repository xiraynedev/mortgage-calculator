package com.xiraynedev;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class MortgageCalculator {
    Scanner scanner = new Scanner(System.in);
    private int principal;
    private float annualInterestRate;
    private byte loanPeriod;
    private final byte PERCENT = 100;
    private final byte MONTHS_IN_YEAR = 12;
    private float monthlyInterestRate;
    private short numberOfPayments;
    private double mortgage;

    public MortgageCalculator() {
        getPrincipal();
        getAnnualInterestRate();
        getLoanPeriod();
        calculateMortgage();
        printMortgageAndPaymentSchedule();
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

    private void getPrincipal() {
        principal = (int) (getUserInput("Principal ($1K - $1M): ", 1000, 1_000_000));
    }

    private void getAnnualInterestRate() {
        annualInterestRate = (float) (getUserInput("Annual Interest Rate (1.0 - 30.0): ", 0, 30));
    }

    private void getLoanPeriod() {
        loanPeriod = (byte) (getUserInput("Loan Period (Years) 1 - 30: ", 0, 30));
    }

    private void calculateMortgage() {
        monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
        numberOfPayments = (short) (loanPeriod * MONTHS_IN_YEAR);

        mortgage = principal
                * (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1));
    }

    private double calculateBalance(short numberOfPaymentsMade) {
        // B = L[(1 + c)**n-(1 + c)**p]/[(1 + c)**n - 1]

        return principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
    }

    private void printMortgageAndPaymentSchedule() {
        printMortgage();
        printPaymentSchedule();
    }

    private void printMortgage() {
        String formattedMortgage = NumberFormat.getCurrencyInstance(Locale.US).format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formattedMortgage);
    }

    private void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= numberOfPayments; month++) {
            double balance = calculateBalance(month);
            System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(balance));
        }
    }
}
