package org.example;

public class UPIPaymentMode implements PaymentMode{
    private final String upiId;

    public UPIPaymentMode(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean makePayment() {
        return true;
    }
}
