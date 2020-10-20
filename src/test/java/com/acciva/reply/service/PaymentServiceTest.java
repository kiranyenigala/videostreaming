package com.acciva.reply.service;

import com.acciva.reply.exception.CardNotRegisteredException;
import com.acciva.reply.model.request.PaymentRequest;
import com.acciva.reply.model.request.RegistrationRequest;
import com.acciva.reply.repository.RegistrationRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

class PaymentServiceTest {

    private PaymentService paymentService;

    @Mock
    private RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentService = new PaymentService(registrationRepository);
    }

    @Test
    void shouldThrowExceptionWhenCreditCardNotRegistered() {
        PaymentRequest request = new PaymentRequest("2233441155667788", 222);
        assertThrows(
                CardNotRegisteredException.class,
                () -> paymentService.processPayment(request)
        );
    }

    @Test
    void shouldPassWhenCreditCardRegistered() {
        List<RegistrationRequest> requests = new ArrayList<>();
        requests.add(createValidRegistrationRequest());
        doReturn(requests).when(registrationRepository).findRegistrations();
        PaymentRequest request = new PaymentRequest("2233441155667788", 222);
        paymentService.processPayment(request);
    }

    private RegistrationRequest createValidRegistrationRequest() {
        return createRegistrationRequest("TestUser",
                "Validpass1",
                "test@gmail.com",
                "1999-02-22",
                "2233441155667788");
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
}