package com.acciva.reply.service;

import com.acciva.reply.exception.UserAlreadyRegisteredException;
import com.acciva.reply.model.request.RegistrationRequest;
import com.acciva.reply.model.response.GetUserRegistrationResponse;
import com.acciva.reply.repository.RegistrationRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Mock
    private RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationService = new RegistrationService(registrationRepository);
    }


    @Test
    void shouldInvokeSaveOnRegistrationRepository() {
        doReturn(false).when(registrationRepository).userExists(anyString());
        registrationService.saveRegistration(new RegistrationRequest());
        verify(registrationRepository).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyRegistered() {
        doReturn(true).when(registrationRepository).userExists(any());
        assertThrows(
                UserAlreadyRegisteredException.class,
                () -> registrationService.saveRegistration(new RegistrationRequest())
        );
    }

    @Test
    void shouldReturnRegisteredUsers() {
        List<RegistrationRequest> registrationRequestList = new ArrayList<>();
        doReturn(registrationRequestList).when(registrationRepository).findRegistrations();
        GetUserRegistrationResponse response = registrationService.findRegistrations();
        assertNotNull(response);
        assertEquals(0, response.getUserDetailsList().size());
    }

    @Test
    void shouldReturnOneRegisteredUsers() {
        List<RegistrationRequest> registrationRequestList = new ArrayList<>();
        registrationRequestList.add(new RegistrationRequest());
        doReturn(registrationRequestList).when(registrationRepository).findRegistrations();
        GetUserRegistrationResponse response = registrationService.findRegistrations();
        assertNotNull(response);
        assertEquals(1, response.getUserDetailsList().size());
    }
}