package org.example;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing cash Payment of $ " + amount);
        return true;
    }
}
