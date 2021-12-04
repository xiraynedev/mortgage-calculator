package com.xiraynedev;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    public static double getUserInput(String prompt, double min, double max) {
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
}
