package com.acciva.reply.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequest {

    @JsonProperty("user_name")
    String userName;

    @JsonProperty("password")
    String password;

    @JsonProperty("email_id")
    String emailId;

    @JsonProperty("date_of_birth")
    String dataOfBirth;

    @JsonProperty("credit_card_number")
    String creditCardNumber;
}
