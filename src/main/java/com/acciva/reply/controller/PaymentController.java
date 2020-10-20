package com.acciva.reply.controller;

import com.acciva.reply.model.request.PaymentRequest;
import com.acciva.reply.service.ValidationService;
import com.acciva.reply.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private ValidationService validationService;

    private PaymentService paymentService;

    @Autowired
    public PaymentController(ValidationService validationService,
                           PaymentService paymentService) {
        this.validationService = validationService;
        this.paymentService = paymentService;
    }

    @PostMapping(
            path = "/payments"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Payment service"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Payment successful"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Invalid Request Parameters"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Credit card not registered with any user"
            )
    })
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        validationService.validate(paymentRequest);
        paymentService.processPayment(paymentRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Payment successful");
    }
}
