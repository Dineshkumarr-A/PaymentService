package com.mycompany.paymentservice.controllers;

import com.mycompany.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.mycompany.paymentservice.interfaces.IPaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController
{
    private IPaymentService _paymentService;

    public PaymentController(IPaymentService paymentService)
    {
        _paymentService = paymentService;
    }

    @PostMapping("/")
    public String createPaymentLink(CreatePaymentLinkRequestDto requestDto)
    {
        String payment = null;
        try {
            payment =  _paymentService.createPaymentLink(requestDto.getOrderId());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return payment;
    }
}
