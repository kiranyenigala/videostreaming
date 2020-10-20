package com.acciva.reply.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRegistrationResponse {

    @JsonProperty("registered_users")
    private List<UserDetails> userDetailsList;
}
