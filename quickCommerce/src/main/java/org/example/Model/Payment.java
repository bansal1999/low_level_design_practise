package org.example.Model;

import org.example.PaymentMode;

public class Payment {
    private PaymentMode paymentMode;

    public Payment(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public boolean makePayment() {
        return paymentMode.makePayment();
    }
}
