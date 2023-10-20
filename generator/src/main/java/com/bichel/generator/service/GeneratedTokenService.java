package com.bichel.generator.service;

import com.bichel.generator.model.DigitsSetModel;
import com.bichel.generator.model.GeneratedTokenModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class GeneratedTokenService {

    private static final Logger logger = LogManager.getLogger(GeneratedTokenService.class);

    /**
     * Generates token using set of digits
     * @param digitsSetModel model with a set of digits
     * @return string in format XXXX-XXXX-XXXX-XXXX
     */
    public GeneratedTokenModel createToken(DigitsSetModel digitsSetModel) {
        GeneratedTokenModel tokenModel = new GeneratedTokenModel();

        Map<Integer, Integer> digitsMap = new HashMap<>();
        int counter = 1;
        for (Integer digit : digitsSetModel.getDigits()) {
            digitsMap.put(counter++, digit);
        }

        Random rand = new Random();
        int min = 1;
        int max = 4;
        final int amountOfGroupsInToken = 4;
        final int amountOfDigitsInGroup = 4;
        final int indexOfLastGroup = 3;

        StringBuilder sbToken = new StringBuilder();
        for(int i = 0; i < amountOfGroupsInToken; i++) {
            StringBuilder part = new StringBuilder();
            for(int j = 0; j < amountOfDigitsInGroup; j++) {
                int randInt = rand.nextInt(max - min + 1) + min;
                part.append(digitsMap.get(randInt));
            }

            sbToken.append(part);
            if(i < indexOfLastGroup) {
                sbToken.append('-');
            }
        }

        tokenModel.setToken(sbToken.toString());

        logger.debug("Generated token is {}", sbToken);

        return tokenModel;
    }
}
