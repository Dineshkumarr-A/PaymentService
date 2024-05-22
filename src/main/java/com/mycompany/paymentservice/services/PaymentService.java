package com.mycompany.paymentservice.services;

import com.mycompany.paymentservice.interfaces.IPaymentGateway;
import com.mycompany.paymentservice.interfaces.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    private IPaymentGateway _paymentGateway;

    public PaymentService(IPaymentGateway paymentGateway)
    {
        _paymentGateway = paymentGateway;
    }

    @Override
    public String createPaymentLink(String orderId) {
        return null;
    }
}
