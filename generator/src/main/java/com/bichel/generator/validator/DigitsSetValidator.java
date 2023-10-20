package com.bichel.generator.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class DigitsSetValidator {
    private static final Logger logger = LogManager.getLogger(DigitsSetValidator.class);
    /**
     * Validates for consisting of the 0-9 digits
     * @param numbers list of numbers
     * @return true is at least one number is not a positive digit nor 0, otherwise false
     */
    public boolean isValidDigits(List<Integer> numbers) {
        return !numbers.stream().anyMatch(number -> number < 0 || number > 9);
    }
}
