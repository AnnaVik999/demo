package com.company;

import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println("введите значения");

        String inputLine = readLine();

        String outputLine = calc(inputLine);

        System.out.println(outputLine);
    }

    static String readLine()
    {
        Scanner scanner = new Scanner(System.in); // ввод значений , например 10 + 8
        return scanner.nextLine(); // присвоение введенных данных
    }

    public static String calc(String inputLine)
    {
        String output;

        String[] substrings = inputLine.split(" "); // разделение элементов пробелом

        if (substrings.length < 3) {
            throw new RuntimeException("строка не является математической операцией");
        }

        if (substrings.length > 3) {
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        char operation = getOperation(substrings[1]);

        int a;
        boolean aIsArabic;
        try {
            a = Integer.parseInt(substrings[0]);
            aIsArabic = true;
        } catch(Exception e) {
            a = readRoman(substrings[0]);
            aIsArabic = false;
        }

        int b;
        boolean bIsArabic;
        try {
            b = Integer.parseInt(substrings[2]);
            bIsArabic = true;
        } catch(Exception e) {
            b = readRoman(substrings[2]);
            bIsArabic = false;
        }

        if (aIsArabic != bIsArabic) {
            throw new RuntimeException("используются одновременно разные системы счисления");
        }

        int result;

        if (operation == '*') {
            result = a * b;
        } else if (operation == '/') {
            result = a / b;
        } else if (operation == '+') {
            result = a + b;
        } else { // operation == '-'
            result = a - b;
        }

        if (aIsArabic) {
            output = String.valueOf(result);//int in string
        } else {
            output = buildRoman(result);
        }

        return output;
    }

    static char getOperation(String str)
    {
        Set<String> operations = Set.of("+", "-", "*", "/");

        if (!operations.contains(str)) {
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        return str.charAt(0);
    }

    static int readRoman(String str)
    {
        switch (str) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new RuntimeException("формат математической операции не удовлетворяет заданию");
        }
    }

    static String buildRoman(int x)
    {
        String result = "";

        if (x == 100) {
            return "C";
        }

        if (x >= 90) {
            x -= 90;
            result += "XC";

            if (x > 0) {
                result += buildRoman(x);
            }

            return result;
        }

        if (x >= 50) {
            x -= 50;
            result += "L";

            if (x > 0) {
                result += buildRoman(x);
            }

            return result;
        }

        if (x >= 40) {
            x -= 40;
            result += "XL";

            if (x > 0) {
                result += buildRoman(x);
            }

            return result;
        }

        if (x >= 10) {
            x -= 10;
            result += "X";

            if (x > 0) {
                result += buildRoman(x);
            }

            return result;
        }

        if (x == 9) {
            return "IX";
        }

        if (x >= 5) {
            x -= 5;
            result += "V";

            if (x > 0) {
                result += buildRoman(x);
            }

            return result;
        }

        if (x == 4) {
            return "IV";
        }

        if (x >= 1) {
            x -= 1;
            result += "I";

            if (x > 0) {
                result += buildRoman(x);
            }

            return result;
        }

        throw new RuntimeException("в римской системе нет отрицательных чисел и 0 ");
    }
}
