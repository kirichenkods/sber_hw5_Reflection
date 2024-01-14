package Proxy;

import Proxy.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int calc(int number) {
        System.out.println("Вызван calc:" + number);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return (number <= 1) ? 1 : number * calc(number - 1);
    }

}
