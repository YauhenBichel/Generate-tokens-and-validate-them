package com.bichel.generator.controller;

import com.bichel.generator.model.GeneratedTokenModel;
import com.bichel.generator.service.GeneratedTokenService;
import com.bichel.generator.mapper.TokenDigitsSetMapper;
import com.bichel.generator.model.DigitsSetModel;
import com.bichel.generator.validator.DigitsSetValidator;
import com.bichel.generator.vo.GeneratedTokenResponse;
import com.bichel.generator.vo.TokenCreationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generator")
public class GeneratorController {

    private static final Logger logger = LogManager.getLogger(GeneratorController.class);

    private final GeneratedTokenService generatedTokenService;
    private final TokenDigitsSetMapper tokenDigitsSetMapper;
    private final DigitsSetValidator digitsSetValidator;

    public GeneratorController(GeneratedTokenService generatedTokenService,
                               TokenDigitsSetMapper tokenDigitsSetMapper,
                               DigitsSetValidator digitsSetValidator) {
        this.generatedTokenService = generatedTokenService;
        this.tokenDigitsSetMapper = tokenDigitsSetMapper;
        this.digitsSetValidator = digitsSetValidator;
    }

    @PostMapping
    public ResponseEntity<GeneratedTokenResponse> createToken(@RequestBody TokenCreationRequest tokenCreationRequest) {
        boolean isValid = digitsSetValidator.isValidDigits(tokenCreationRequest.getDigits());
        if(!isValid) {
            logger.debug("Request contains not valid digits for token");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DigitsSetModel model = tokenDigitsSetMapper.toTokenDigitsSet(tokenCreationRequest);
        GeneratedTokenModel tokenModel = generatedTokenService.createToken(model);

        return new ResponseEntity<>(tokenDigitsSetMapper.toTokenResponse(tokenModel), HttpStatus.CREATED);
    }
}
