package org.example;

public class UPIPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI Payment of $ " + amount);
        return true;
    }
}
