package org.example.payment;

public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // logic to process credit card payment
        return true; // return true if payment successful
    }
}
