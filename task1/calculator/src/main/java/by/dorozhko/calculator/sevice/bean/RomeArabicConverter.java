package by.dorozhko.calculator.sevice.bean;

import java.util.*;

public class RomeArabicConverter {

    private List<RomanNumeral> romanNumerals;

    public RomeArabicConverter() {
        romanNumerals
                = new ArrayList<>(Arrays.asList(RomanNumeral.values()));
        Collections.sort(romanNumerals,
                Comparator.comparingInt(RomanNumeral::getValue).reversed());

    }

    public int toArabic(String rome) {
        String romanNumeral = rome.toUpperCase();
        int result = 0;

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(rome + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public String toRome(int arabic) {
        if ((arabic <= 0) || (arabic > 100)) {
            throw new IllegalArgumentException(arabic + " is not in range (0,100]");
        }

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((arabic > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= arabic) {
                sb.append(currentSymbol.name());
                arabic -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
