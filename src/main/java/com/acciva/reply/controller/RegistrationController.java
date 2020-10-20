package com.acciva.reply.controller;

import com.acciva.reply.model.request.RegistrationRequest;
import com.acciva.reply.model.response.GetUserRegistrationResponse;
import com.acciva.reply.model.response.RegistrationResponse;
import com.acciva.reply.service.RegistrationService;
import com.acciva.reply.service.ValidationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private ValidationService validationService;

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(ValidationService validationService,
                           RegistrationService registrationService) {
        this.validationService = validationService;
        this.registrationService = registrationService;
    }


    @PostMapping(
            path = "/registration"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Create User Registration"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Registration successful"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Invalid Request Parameters"
            ),
            @ApiResponse(
                    code = 403,
                    message = "User is under the age of 18"
            ),
            @ApiResponse(
                    code = 409,
                    message = "User Name already exists"
            )
    })
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        validationService.validate(registrationRequest);
        registrationService.saveRegistration(registrationRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RegistrationResponse("User registration successful"));
    }


    @GetMapping(
            path = "/registration"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Get registered users"
    )
    public ResponseEntity<GetUserRegistrationResponse> registeredUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registrationService.findRegistrations());
    }
}
