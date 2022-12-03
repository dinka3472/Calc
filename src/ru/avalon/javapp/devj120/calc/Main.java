package ru.avalon.javapp.devj120.calc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Main {

    public static String calc(String input) throws IOException {
        int result;
        int num1;
        int num2;
        char operator;

        input = input.replace(" ", "");
        String[] numbers = input.split("[+\\-*/]");
        if (numbers.length > 2) {
            throw new IOException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор ");
        }
        if (numbers.length == 1) {
            throw new IOException("Выражение не является математической операцией");
        }

        operator = findOperator(input);

        if (isDigit(numbers[0]) && isDigit(numbers[1])) {
            num1 = Integer.parseInt(numbers[0]);
            num2 = Integer.parseInt(numbers[1]);
            if (num1 > 10 || num2 > 10 || num1< 1 || num2 < 1) {
                throw new IOException("Числа могут быть только от 1 до 10");
            }
            result = findResult(num1, num2, operator);
        } else if (isRoman(numbers[0])&&isRoman(numbers[1])) {
            num1 = Converter.romanToInt(numbers[0]);
            num2 = Converter.romanToInt(numbers[1]);
            if (num1 > 10 || num2 > 10 || num1< 1 || num2 < 1) {
                throw new IOException("Числа могут быть только от I до X");
            }
                result = findResult(num1,num2,operator);
                    if (result < 1) {
                        throw new IOException("Результат работы с римскими числами должен быть  > 1");
                    }
           return Converter.intToRoman(result);

        } else {
            throw new IOException("Должна использоваться одна система счисления");
        }

        return Integer.toString(result);
    }


    private static int findResult(int number1, int number2, char operator) {
        int result = 0;
        if (operator == '+') {
            result = number1 + number2;
        } else  if (operator == '-') {
            result = number1-number2;
        } else  if (operator == '*') {
            result = number1*number2;
        } else if (operator == '/') {
            result = number1/number2;
        }
        return result;
    }


    private static char findOperator(String number) {
        char[] chars = {'-', '+', '*', '/'};
        char operator = 0;
        for (char ch: chars) {
            int p = 0;
            while (p < number.length()) {
                if (number.charAt(p) == ch) {
                   return operator = number.charAt(p);
                }
                p++;
            }
        } return operator;
    }


    private static boolean isRoman(String s) {
        int p = 0;
        int c = 0;
        char[] chars = {'I', 'V', 'X', 'L', 'C', 'M', 'D'};
        while ( p < s.length()) {
            for (char ch: chars) {
                if (ch == s.charAt(p)) {
                    c++;
                }
            }
            if (c == 0 ) return  false;
            p++;
            c = 0;
        }
        return true;
    }

    private static boolean isDigit(String number) {
        try {
            Integer.parseInt(number);
            return  true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
	// write your code here

        System.out.println("Введите выражение для вычисления:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Результат вычисления: " );
        System.out.println(calc(input));
    }
}