package com.bichel.validator.controller;

import com.bichel.validator.service.ValidatorTokenService;
import com.bichel.validator.vo.TokenValidationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("validator")
public class ValidatorController {

    private static final Logger logger = LogManager.getLogger(ValidatorController.class);

    private final ValidatorTokenService validatorTokenService;

    public ValidatorController(ValidatorTokenService validatorTokenService) {
        this.validatorTokenService = validatorTokenService;
    }

    @GetMapping("/{token}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<TokenValidationResponse> validateToken(@PathVariable("token") final String token) {

        logger.debug("Token is {}", token);

        boolean isValid = validatorTokenService.validate(token);
        TokenValidationResponse response = new TokenValidationResponse();
        response.setValid(isValid);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
