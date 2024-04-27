package com.customerservice.customerservice.service;

import com.customerservice.customerservice.dto.AddCardObject;
import com.customerservice.customerservice.dto.AddCustomerDto;
import com.customerservice.customerservice.dto.CustomerAddressDto;
import com.customerservice.customerservice.entity.MyCustomer;
import com.customerservice.customerservice.repository.CustomerAddressRepository;
import com.customerservice.customerservice.repository.CustomerRepository;
import com.customerservice.customerservice.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;
    private final PaymentRepository paymentRepository;

    public void addCustomer(AddCustomerDto addCustomerDto) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        Map<String, Object> customerParam = new HashMap<String, Object>();
        customerParam.put("email", addCustomerDto.getEmail());
        customerParam.put("name", addCustomerDto.getFirstName() + " " + addCustomerDto.getLastName());
        Customer stripeCustomer = Customer.create(customerParam);
        MyCustomer myCustomer = MyCustomer.builder()
                .firstName(addCustomerDto.getFirstName())
                .lastName(addCustomerDto.getLastName())
                .email(addCustomerDto.getEmail())
                .StripeId(stripeCustomer.getId())
                .build();
        customerRepository.save(myCustomer);
    }

    public void addCard(AddCardObject addCardObject) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        Customer customer = Customer.retrieve(addCardObject.getStripeUserId());
        Map<String, Object> cardParam = new HashMap<>();
        cardParam.put("number", addCardObject.getCardNumber());
        cardParam.put("exp_month", addCardObject.getExpMonth());
        cardParam.put("exp_year", addCardObject.getExpYear());
        cardParam.put("cvc", addCardObject.getCvc());
        Map<String, Object> tokenParam = new HashMap<>();
        tokenParam.put("card", cardParam);
        Token token = Token.create(tokenParam);
        Map<String, Object> source = new HashMap<>();
        source.put("source", token.getId());
        customer.getSources().create(source);
    }

    public void addAddress(CustomerAddressDto customerAddressDto)  throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        Customer customer = Customer.retrieve(customerAddressDto.getStripeUserId());
        Map<String, Object> addressParam = new HashMap<>();
        addressParam.put("line1", customerAddressDto.getLine1());
        addressParam.put("city", customerAddressDto.getCity());
        addressParam.put("postal_code", customerAddressDto.getPostalCode());
        addressParam.put("country", customerAddressDto.getCountry());
        Map<String, Object> shipping = new HashMap<>();
        shipping.put("address", addressParam);
        Map<String, Object> shippingParams = new HashMap<>();
        shippingParams.put("shipping", shipping);
        customer.update(shippingParams);
    }
}
