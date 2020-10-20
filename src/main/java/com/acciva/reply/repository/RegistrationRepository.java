package com.acciva.reply.repository;

import com.acciva.reply.model.request.RegistrationRequest;
import java.util.Collection;

public interface RegistrationRepository {

    void save(RegistrationRequest registrationRequest);

    Collection<RegistrationRequest> findRegistrations();

    boolean userExists(String userName);
}
