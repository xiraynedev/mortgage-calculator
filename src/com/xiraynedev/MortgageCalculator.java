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

    public  MortgageCalculator() {
        principal = getPrincipal();
        annualInterestRate = getAnnualInterestRate();
        loanPeriod = getLoanPeriod();
        mortgage = calculateMortgage();
        formatAndPrintMortgage(mortgage);
    }

    private int getPrincipal() {
        while (true) {
            System.out.print("Principal ($1K - $1M): ");
            principal = scanner.nextInt();
            if (principal >= 1000 && principal <= 1_000_000) {
                break;
            }
            System.out.println("Enter a number between $1,000 and $1,000,000");
        }
        return principal;
    }

    private float getAnnualInterestRate() {
        while (true) {
            System.out.print("Annual Interest Rate (1.0 - 30.0): ");
            annualInterestRate = scanner.nextFloat();
            if (annualInterestRate > 0 && annualInterestRate <= 30) {
                break;
            }
            System.out.println("Enter a number between 1 and 30");
        }
        return annualInterestRate;
    }

    private byte getLoanPeriod() {
        while (true) {
            System.out.print("Loan Period (Years) 1 - 30: ");
            loanPeriod = scanner.nextByte();
            if (loanPeriod > 0 && loanPeriod <= 30) {
                break;
            }
            System.out.println("Enter a number between 1 and 30");
        }
        return loanPeriod;
    }

    private double calculateMortgage() {
        final byte PERCENT = 100;
        final byte MONTHS_IN_YEAR = 12;
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
        short numberOfPayments = (short) (loanPeriod * MONTHS_IN_YEAR);

        return principal
                * (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) -1));
    }

    private void formatAndPrintMortgage(double mortgage) {
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(mortgage));
    }
}
