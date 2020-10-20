package com.acciva.reply.service;

import com.acciva.reply.exception.InvalidRequestException;
import com.acciva.reply.model.request.PaymentRequest;
import com.acciva.reply.model.request.RegistrationRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private static final Pattern USER_NAME_REGEX = Pattern.compile("^[A-Za-z0-9+]+");
    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
//    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.]+@[A-Za-z0-9.]+$");
    private static final Pattern CREDIT_CARD_REGEX = Pattern.compile("((?:(?:\\d{4}[- ]){3}\\d{4}|\\d{16}))(?![\\d])");

    public void validate(RegistrationRequest registrationRequest) {
        List<String> errorMessages = new ArrayList<>();
        validateCreditCard(registrationRequest.getCreditCardNumber(), errorMessages);
        validateDateOfBirth(registrationRequest.getDataOfBirth(), errorMessages);
        validateEmailId(registrationRequest.getEmailId(), errorMessages);
        validatePassword(registrationRequest.getPassword(), errorMessages);
        validateUserName(registrationRequest.getUserName(), errorMessages);
        if (errorMessages.size() > 0) {
            throw new InvalidRequestException(errorMessages);
        }
    }

    public void validate(PaymentRequest paymentRequest) {
        List<String> errorMessages = new ArrayList<>();
        validateCreditCard(paymentRequest.getCreditCardNumber(), errorMessages);
        validateAmount(paymentRequest.getAmount(), errorMessages);
        if (errorMessages.size() > 0) {
            throw new InvalidRequestException(errorMessages);
        }
    }

    private void validateAmount(Integer amount, List<String> errorMessages) {
        if (amount == null || amount < 0 || amount >= 1000) {
            errorMessages.add("Invalid payment amount " + amount);
        }
    }

    private void validateDateOfBirth(String dateOfBirth, List<String> errorMessages) {
        try {
            LocalDate.parse(dateOfBirth);
        } catch (DateTimeParseException dte) {
            errorMessages.add("Invalid date of birth format " + dateOfBirth);
        }
    }

    private void validateUserName(String userName, List<String> errorMessages) {
        if (!USER_NAME_REGEX
                .matcher(userName)
                .find()) {
            errorMessages.add("Invalid user name " + userName);
        }
    }

    private void validateEmailId(String emailId, List<String> errorMessages) {
        if (!EMAIL_REGEX
                .matcher(emailId)
                .find()) {
            errorMessages.add("Invalid email id " + emailId);
        }
    }

    private void validatePassword(String password, List<String> errorMessages) {
        if (!PASSWORD_REGEX
                .matcher(password)
                .find()) {
            errorMessages.add("Invalid password " + password);
        }
    }

    private void validateCreditCard(String creditCard, List<String> errorMessages) {
        if (!CREDIT_CARD_REGEX
                .matcher(creditCard)
                .find()) {
            errorMessages.add("Credit card number has to be 16 digits long");
        }
    }
}
