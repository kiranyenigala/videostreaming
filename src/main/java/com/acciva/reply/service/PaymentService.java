package com.acciva.reply.service;

import com.acciva.reply.exception.CardNotRegisteredException;
import com.acciva.reply.model.request.PaymentRequest;
import com.acciva.reply.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private RegistrationRepository registrationRepository;

    @Autowired
    PaymentService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public void processPayment(PaymentRequest paymentRequest) {
        if (!cardRegisteredWithUser(paymentRequest.getCreditCardNumber())) {
            throw new CardNotRegisteredException();
        }
        // Process Payment
    }

    private boolean cardRegisteredWithUser(String cardNumber) {
        return this.registrationRepository
                .findRegistrations()
                .stream()
                .filter(registration -> registration.getCreditCardNumber().equals(cardNumber))
                .findAny()
                .isPresent();
    }
}
