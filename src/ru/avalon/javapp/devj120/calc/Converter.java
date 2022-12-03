package ru.avalon.javapp.devj120.calc;

import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;

class Converter {
    private static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4 ,1};
    private static final String [] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    static String intToRoman (int number) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length && number > 0; i++) {
            while (number >= numbers[i]) {
                number -= numbers[i];
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }


    static int romanToInt(String s) {
        int result = 0;
        int prev = 0;

        for (int i = s.length()-1; i>= 0; i--) {
            for (int j = 0; j < str.length; j++) {
                if (str[j].equals(Character.toString(s.charAt(i)))) {
                    int curr = numbers[j];
                    if (curr < prev) {
                        result -= numbers[j];
                    } else {
                        result += numbers[j];
                    }
                    prev = curr;
                }
            }
        }
        return  result;
    }
}
