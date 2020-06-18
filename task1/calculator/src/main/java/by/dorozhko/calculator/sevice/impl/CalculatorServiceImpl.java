package by.dorozhko.calculator.sevice.impl;

import by.dorozhko.calculator.sevice.CalculatorService;
import by.dorozhko.calculator.sevice.bean.RomeArabicConverter;
import by.dorozhko.calculator.sevice.bean.Validator;
import by.dorozhko.calculator.sevice.exception.InvalidArithmeticOperation;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    boolean isRomeCalculation;

    @Override
    public String calculate(String expression) {
        String[] values = splitExpression(expression);

        int firstValue = extractValue(values[0]);
        int secondValue = extractValue(values[2]);

        validateValues(firstValue, secondValue);

        int result = calculateArabic(firstValue, secondValue, values[1]);

        return isRomeCalculation ?
                new RomeArabicConverter().toRome(result)
                : Integer.toString(result);
    }

    private String[] splitExpression(String expression) {
        String[] values = expression.split(" ");
        if (values.length != 3) {
            throw new IllegalArgumentException("Incorrect expression");
        }
        return values;
    }

    private int extractValue(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            isRomeCalculation = true;
            RomeArabicConverter converter = new RomeArabicConverter();
            return converter.toArabic(value);
        }

    }

    private void validateValues(int firstValue, int secondValue) {
        Validator validator = new Validator();
        boolean isValidValues = validator.validateValue(firstValue) && validator.validateValue(secondValue);
        if (!isValidValues) {
            throw new IllegalArgumentException("Invalid input values");
        }
    }

    private int calculateArabic(int firstValue,
                                int secondValue,
                                String operation) {
        switch (operation) {
            case "+":
                return firstValue + secondValue;
            case "-":
                return firstValue - secondValue;
            case "*":
                return firstValue * secondValue;
            case "/":
                return firstValue / secondValue;
            default:
                throw new IllegalArgumentException("Invalid operation!");
        }
    }
}
