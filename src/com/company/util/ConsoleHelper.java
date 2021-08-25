package com.company.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleHelper {
    public static int inputInt(String message) {
        boolean isNumber;
        int number = 0;

        do {
            try {
                isNumber = true;
                Scanner input = new Scanner(System.in);

                System.out.print(message);
                number = input.nextInt();
            } catch (Exception e) {
                isNumber = false;
            }

        } while (isNumber == false);

        return number;
    }

    public static int inputInt(String message, int minValue, int maxValue) {
        boolean isNumber;
        int number = 0;

        do {
            try {
                isNumber = true;
                Scanner input = new Scanner(System.in);

                System.out.print(message);
                number = input.nextInt();

                if (number < minValue || number > maxValue) {
                    throw new Exception();
                }

            } catch (Exception e) {
                isNumber = false;
            }

        } while (isNumber == false);

        return number;
    }

    public static int inputInt(String message, ArrayList<Integer> allowValues) {
        boolean isNumber;
        int number = 0;

        do {
            try {
                isNumber = true;
                Scanner input = new Scanner(System.in);

                System.out.print(message);
                number = input.nextInt();

                int findIndex = allowValues.indexOf(number);

                if (findIndex == -1) {
                    throw new Exception();
                }

            } catch (Exception e) {
                isNumber = false;
            }

        } while (isNumber == false);

        return number;
    }

    public static double inputDouble(String message) {
        boolean isNumber;
        double number = 0;

        do {
            try {
                isNumber = true;
                Scanner input = new Scanner(System.in);

                System.out.print(message);
                number = input.nextDouble();
            } catch (Exception e) {
                isNumber = false;
            }

        } while (isNumber == false);

        return number;
    }

    public static String inputString(String message) {
        boolean isString;
        String string = "";

        do {
            try {
                isString = true;
                Scanner input = new Scanner(System.in);

                System.out.print(message);
                string = input.nextLine();
            } catch (Exception e) {
                isString = false;
            }

        } while (isString == false);

        return string;
    }

    public static void printlnMessage(String message) {
        System.out.println(message);
    }

    public static void printlnDivider(){
        System.out.println("--------");
    }

    public static void waitEnter() throws IOException {
        System.in.read();
    }


}