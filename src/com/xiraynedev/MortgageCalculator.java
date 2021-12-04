package com.xiraynedev;

import java.text.NumberFormat;
import java.util.Locale;

public class MortgageCalculator {
    private int principal;
    private float annualInterestRate;
    private byte loanPeriod;
    private float monthlyInterestRate;
    private short numberOfPayments;
    private double mortgage;

    public MortgageCalculator() {
        getPrincipal();
        getAnnualInterestRate();
        getLoanPeriod();
        calculateMortgage();
        printMortgage();
        printPaymentSchedule();
    }

    private void getPrincipal() {
        principal = (int) (Console.getUserInput("Principal ($1K - $1M): ", 1000, 1_000_000));
    }

    private void getAnnualInterestRate() {
        annualInterestRate = (float) (Console.getUserInput("Annual Interest Rate (1.0 - 30.0): ", 0, 30));
    }

    private void getLoanPeriod() {
        loanPeriod = (byte) (Console.getUserInput("Loan Period (Years) 1 - 30: ", 0, 30));
    }

    private void calculateMortgage() {
        byte PERCENT = 100;
        byte MONTHS_IN_YEAR = 12;
        monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
        numberOfPayments = (short) (loanPeriod * MONTHS_IN_YEAR);

        mortgage = principal
                * (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1));
    }

    private double calculateBalance(short numberOfPaymentsMade) {
        return principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
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
