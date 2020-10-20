package com.acciva.reply.repository;

import com.acciva.reply.model.request.RegistrationRequest;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class InMemoryRegistrationRepository implements RegistrationRepository {

    private Map<String, RegistrationRequest> userRegistrations = new ConcurrentHashMap<>();

    @Override
    public void save(RegistrationRequest registrationRequest) {
        userRegistrations.putIfAbsent(registrationRequest.getUserName(), registrationRequest);
    }

    @Override
    public Collection<RegistrationRequest> findRegistrations() {
        return userRegistrations.values();
    }

    @Override
    public boolean userExists(String userName) {
        return userRegistrations.containsKey(userName);
    }


}
