package com.mycompany.paymentservice.services.paymentgateways;

import com.mycompany.paymentservice.interfaces.IPaymentGateway;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway {
    @Override
    public String createPaymentLint(String orderId) {
        return null;
    }
}
