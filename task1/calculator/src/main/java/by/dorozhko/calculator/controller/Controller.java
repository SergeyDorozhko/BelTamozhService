package by.dorozhko.calculator.controller;

import by.dorozhko.calculator.sevice.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@org.springframework.stereotype.Controller
public class Controller {

    private CalculatorService service;

    @Autowired
    public Controller(CalculatorService service) {
        this.service = service;
    }

    @PostConstruct
    public void startConsole() {
        System.out.println("Input mathematical expression:");
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        String result = service.calculate(expression);
        System.out.println("result : " + result);
    }
}
