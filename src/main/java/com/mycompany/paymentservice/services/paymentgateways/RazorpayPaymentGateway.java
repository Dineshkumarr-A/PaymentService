package com.mycompany.paymentservice.services.paymentgateways;

import com.mycompany.paymentservice.interfaces.IPaymentGateway;
import com.razorpay.PaymentLink;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@Primary
public class RazorpayPaymentGateway implements IPaymentGateway {

    private RazorpayClient _razorpayClient;


    public RazorpayPaymentGateway(RazorpayClient razorpayClient)
    {
        _razorpayClient = razorpayClient;
    }
    @Override
    public String createPaymentLint(String orderId) {

        Instant expireBy = Instant.now().plus(15, ChronoUnit.MINUTES);

        // Convert expireBy to epoch seconds
        long expireByEpochSeconds = expireBy.getEpochSecond();

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",expireByEpochSeconds);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for policy no #" + orderId);
        JSONObject customer = new JSONObject();
        customer.put("name","+919000090000");
        customer.put("contact","Gaurav Kumar");
        customer.put("email","gaurav.kumar@example.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = null;
        try {
            payment = _razorpayClient.paymentLink.create(paymentLinkRequest);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return payment.toString();
    }
}
