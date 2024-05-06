package com.customerservice.customerservice.service;

import com.customerservice.customerservice.JwtService;
import com.customerservice.customerservice.dto.AddCustomerDto;
import com.customerservice.customerservice.dto.CustomerAddressDto;
import com.customerservice.customerservice.dto.CustomerSessionDto;
import com.customerservice.customerservice.entity.MyCustomer;
import com.customerservice.customerservice.kafka.KafkaProducer;
import com.customerservice.customerservice.repository.CustomerRepository;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.*;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    private static final String jwtSecret = "d740b4e7547111cee19518ffef9b95645de3c346043281e52caaf7c48514e04b";

    private final CustomerRepository customerRepository;

    private final KafkaProducer kafkaProducer;
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<String> addCard(HttpServletRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        String cardToken = "tok_visa";
        Customer customer = Customer.retrieve(userId);
        PaymentMethodCreateParams params =
                PaymentMethodCreateParams.builder()
                        .setType(PaymentMethodCreateParams.Type.CARD)
                        .setCard(
                                PaymentMethodCreateParams.Token.builder()
                                        .setToken(cardToken)  // Set the card token instead of card details
                                        .build()
                        )
                        .build();
        try {
            PaymentMethod resource = PaymentMethod.create(params);
            PaymentMethodAttachParams attachParams =
                    PaymentMethodAttachParams.builder().setCustomer(userId).build();
            PaymentMethod paymentMethod = resource.attach(attachParams);
            // Handle the created paymentMethod as needed
            return new ResponseEntity<>("PaymentMethod created: " +  resource.getId(), HttpStatus.CREATED);

        } catch (StripeException e) {
            // Handle any errors that may occur during the creation of PaymentMethod
            return new ResponseEntity<>("Error creating PaymentMethod: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addAddress(CustomerAddressDto customerAddressDto, HttpServletRequest request)  throws StripeException {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        Stripe.apiKey = stripeSecretKey;
        Customer customer = Customer.retrieve(userId);
        Map<String, Object> addressParam = new HashMap<>();
        addressParam.put("line1", customerAddressDto.getLine1());
        addressParam.put("city", customerAddressDto.getCity());
        addressParam.put("postal_code", customerAddressDto.getPostalCode());
        addressParam.put("country", customerAddressDto.getCountry());
        Map<String, Object> shipping = new HashMap<>();
        shipping.put("address", addressParam);
        shipping.put("name", customerAddressDto.getFirstName() + " " + customerAddressDto.getLastName());
        Map<String, Object> shippingParams = new HashMap<>();
        shippingParams.put("shipping", shipping);
        customer.update(shippingParams);
        return new ResponseEntity<>("Address added successfully", HttpStatus.CREATED);
    }

    public String authenticate(String email, String password) {
        MyCustomer customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            String my_token = JwtService.generateToken(customer.getStripeId());
            return my_token;
        }
        return null;
    }

    public MyCustomer registerCustomer(MyCustomer customerDetails) throws StripeException {
        MyCustomer tempCustomer = customerRepository.findByEmail(customerDetails.getEmail());
        if (tempCustomer != null) {
            return null;
        }
        // Hash the password
        String hashedPassword = passwordEncoder.encode(customerDetails.getPassword());
        customerDetails.setPassword(hashedPassword);
        Stripe.apiKey = stripeSecretKey;
        Map<String, Object> customerParam = new HashMap<String, Object>();
        customerParam.put("email", customerDetails.getEmail());
        customerParam.put("name", customerDetails.getFirstName() + " " + customerDetails.getLastName());
        Customer stripeCustomer = Customer.create(customerParam);
        customerDetails.setStripeId(stripeCustomer.getId());
        customerRepository.save(customerDetails);
        return  customerDetails;
    }

    public void createSession(String email) {
        MyCustomer customer = customerRepository.findByEmail(email);
        String stripeId = customer.getStripeId();
        String name = customer.getFirstName() + " " + customer.getLastName();
        CustomerSessionDto customerSessionDto = CustomerSessionDto.builder()
                .stripeId(stripeId)
                .name(name)
                .build();
        kafkaProducer.createSession(customerSessionDto);
    }

    public String getTokenFromCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
    public static String getIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<PaymentMethod> getAllPaymentMethods(HttpServletRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        Customer customer = Customer.retrieve(userId);
        CustomerListPaymentMethodsParams params =
                CustomerListPaymentMethodsParams.builder().build();
        PaymentMethodCollection paymentMethods = customer.listPaymentMethods(params);

        return paymentMethods.getData();
    }

    public ResponseEntity<String> createOrder(HttpServletRequest request) throws StripeException {
        double totalAmount = 500.0;
        Stripe.apiKey = stripeSecretKey;
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        ////////////////////////////////////////////////////////////////////////////////
        Customer customer = Customer.retrieve(userId);
        CustomerListPaymentMethodsParams params =
                CustomerListPaymentMethodsParams.builder().build();
        PaymentMethodCollection paymentMethods = customer.listPaymentMethods(params);
        if (paymentMethods.getData().isEmpty()) {
            return new ResponseEntity<>("Error please add a payment method first !", HttpStatus.BAD_REQUEST);

        }
        if (customer.getShipping() == null) {
            return new ResponseEntity<>("Error please add a shipping address first !", HttpStatus.BAD_REQUEST);
        }
        //////////////////////////////////////////////////////////////////////////////////

        PaymentIntentCreateParams.Shipping.builder()
                .setName(customer.getName())
                .setPhone(customer.getPhone())
                .setAddress(
                        PaymentIntentCreateParams.Shipping.Address.builder()
                        .setCity(customer.getShipping().getAddress().getCity())
                        .setCountry(customer.getShipping().getAddress().getCountry())
                        .setLine1(customer.getShipping().getAddress().getLine1())
                        .setPostalCode(customer.getShipping().getAddress().getPostalCode())
                        .build()
                )
                .build();
        // Create a PaymentIntent specifying the customer, currency and amount
        PaymentIntentCreateParams paymentIntentParams = PaymentIntentCreateParams.builder()
                .setCustomer(userId)
                .setCurrency("usd") // Update with your currency code
                .setAmount((long) (totalAmount * 100)) // Convert double to long with cents conversion
                .setPaymentMethod(paymentMethods.getData().getLast().getId())
                .setShipping(
                        PaymentIntentCreateParams.Shipping.builder()
                                .setName(customer.getName())
                                .setPhone(customer.getPhone())
                                .setCarrier("carrier")
                                .setTrackingNumber("1")
                                .setAddress(
                                        PaymentIntentCreateParams.Shipping.Address.builder()
                                                .setCity(customer.getShipping().getAddress().getCity())
                                                .setCountry(customer.getShipping().getAddress().getCountry())
                                                .setLine1(customer.getShipping().getAddress().getLine1())
                                                .setPostalCode(customer.getShipping().getAddress().getPostalCode())
                                                .build()
                                )
                                .build()
                )
                .build();
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);

            // Handle the successful payment intent
            return new ResponseEntity<>("Order created: " + paymentIntent.getId(), HttpStatus.CREATED);
        } catch (StripeException e) {
            // Handle any errors that may occur during PaymentIntent creation
            return new ResponseEntity<>("Error creating order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        // ... rest of the code remains the same as before ...
    }
}
