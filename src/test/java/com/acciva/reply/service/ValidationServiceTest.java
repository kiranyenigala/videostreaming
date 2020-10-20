package com.acciva.reply.service;

import com.acciva.reply.exception.InvalidRequestException;
import com.acciva.reply.model.request.PaymentRequest;
import com.acciva.reply.model.request.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService();
    }

    @Test
    void shouldFailOnInvalidRegistrationData() {
        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                () -> validationService.validate(createInvalidRegistrationRequest())
        );
        assertNotNull(exception);
    }

    @Test
    void shouldPassOnValidRegistrationData() {
        validationService.validate(createValidRegistrationRequest());
    }

    @Test
    void shouldFailOnInvalidPaymentRequest() {
        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                () -> validationService.validate(createInvalidPaymentRequest())
        );
        assertNotNull(exception);
    }

    @Test
    void shouldPassOnInvalidPaymentRequest() {
        validationService.validate(createValidPaymentRequest());
    }

    private RegistrationRequest createInvalidRegistrationRequest() {
        return createRegistrationRequest("TestUser-- 1111",
                "Invalid",
                "test.com",
                "199-13-32",
                "223344552211");
    }

    private RegistrationRequest createValidRegistrationRequest() {
        return createRegistrationRequest("TestUser",
                "Validpass1",
                "test@gmail.com",
                "1999-02-22",
                "1122334455667788");
    }

    private RegistrationRequest createRegistrationRequest(String userName, String password,
                                                          String emailId, String dataOfBirth,
                                                          String creditCardNumber) {
        RegistrationRequest request = new RegistrationRequest();
        request.setCreditCardNumber(creditCardNumber);
        request.setDataOfBirth(dataOfBirth);
        request.setEmailId(emailId);
        request.setPassword(password);
        request.setUserName(userName);
        return request;
    }

    private PaymentRequest createInvalidPaymentRequest() {
        return createPaymentRequest("112233445566778", 12);
    }

    private PaymentRequest createValidPaymentRequest() {
        return createPaymentRequest("1122334455667788", 123);
    }

    private PaymentRequest createPaymentRequest(String creditCard,
                                                Integer amount) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(amount);
        paymentRequest.setCreditCardNumber(creditCard);
        return paymentRequest;
    }
}