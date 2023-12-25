package com.galaxymerchant.util;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeralUtils {
    public static boolean isRomanNumeral(String romanNumeral) {
        return romanNumeral.matches("^[IVXLCDM]+$") && !romanNumeral.matches(".*(I{4,}|X{4,}|C{4,}|M{4,}).*");
    }

    public static int romanToNumber(String romanNumeral) {
        if(!isRomanNumeral(romanNumeral)) {
            throw new IllegalArgumentException("Requested number is in invalid format");
        } else {
            Map<Character, Integer> romanMap = new HashMap<>();
            romanMap.put('I', 1);
            romanMap.put('V', 5);
            romanMap.put('X', 10);
            romanMap.put('L', 50);
            romanMap.put('C', 100);
            romanMap.put('D', 500);
            romanMap.put('M', 1000);

            int decimalValue = 0;
            int prevValue = 0;

            for (int i = romanNumeral.length() - 1; i >= 0; i--) {
                int currentValue = romanMap.get(romanNumeral.charAt(i));

                if (currentValue < prevValue) {
                    decimalValue -= currentValue;
                } else {
                    decimalValue += currentValue;
                }

                prevValue = currentValue;
            }

            return decimalValue;
        }
    }
}
