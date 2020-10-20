package com.acciva.reply.service;

import com.acciva.reply.exception.UserAlreadyRegisteredException;
import com.acciva.reply.model.request.RegistrationRequest;
import com.acciva.reply.model.response.GetUserRegistrationResponse;
import com.acciva.reply.model.response.UserDetails;
import com.acciva.reply.repository.RegistrationRepository;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    @Autowired
    RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public void saveRegistration(RegistrationRequest registrationRequest) {
        if (registrationRepository.userExists(registrationRequest.getUserName())) {
            throw new UserAlreadyRegisteredException(registrationRequest.getUserName());
        }
        registrationRepository.save(registrationRequest);
    }

    public GetUserRegistrationResponse findRegistrations() {
        return new GetUserRegistrationResponse(registrationRepository
                .findRegistrations()
                .stream()
                .map(registration -> new UserDetails(registration.getUserName(), registration.getEmailId()))
                .collect(Collectors.toList()));
    }
}
