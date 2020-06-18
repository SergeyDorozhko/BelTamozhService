package by.dorozhko.calculator.sevice.bean;

public class Validator {

    public boolean validateValue(int value) {
        return (value > 0 && value <= 10) ?
                true
                : false;
    }
}
