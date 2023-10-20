package com.bichel.generator.mapper;

import com.bichel.generator.model.DigitsSetModel;
import com.bichel.generator.model.GeneratedTokenModel;
import com.bichel.generator.vo.GeneratedTokenResponse;
import com.bichel.generator.vo.TokenCreationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public final class TokenDigitsSetMapper {
    private static final Logger logger = LogManager.getLogger(TokenDigitsSetMapper.class);

    public DigitsSetModel toTokenDigitsSet(TokenCreationRequest request) {
        DigitsSetModel digitsSetModel = new DigitsSetModel();
        digitsSetModel.setDigits(request.getDigits());

        return digitsSetModel;
    }

    public GeneratedTokenResponse toTokenResponse(GeneratedTokenModel tokenModel) {
        GeneratedTokenResponse tokenResponse = new GeneratedTokenResponse();
        tokenResponse.setToken(tokenModel.getToken());

        return tokenResponse;
    }
}
