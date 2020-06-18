package by.dorozhko.calculator.sevice.exception;

public class InvalidArithmeticOperation extends RuntimeException {
    public InvalidArithmeticOperation(String message){
        super("Check arithmetic operation! Your operation: " + message);
    }
}
