package com.xiraynedev;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public abstract class MortgageReport {
    public static void printMortgage(double mortgage) {
        String formattedMortgage = NumberFormat.getCurrencyInstance(Locale.US).format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formattedMortgage);
    }

    public static void printPaymentSchedule(ArrayList balance) {
        System.out.println("----------------");
        System.out.println("PAYMENT SCHEDULE");
        for (Object value : balance) {
            System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(value));
        }
    }
}
