package org.example;

public class CardPaymentMode implements PaymentMode {
    private final String cardNumber;
    private final String cardHolderName;

    public CardPaymentMode(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean makePayment() {
        return true;
    }
}
