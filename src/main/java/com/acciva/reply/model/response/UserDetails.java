package com.acciva.reply.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @JsonProperty("user_name")
    String userName;

    @JsonProperty("email_id")
    String emailId;
}
