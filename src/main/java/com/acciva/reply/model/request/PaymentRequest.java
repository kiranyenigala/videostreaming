package com.acciva.reply.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @JsonProperty("credit_card_number")
    String creditCardNumber;

    @JsonProperty("amount")
    Integer amount;
}
