package com.bichel.validator.service;

import org.springframework.stereotype.Service;

@Service
public class ValidatorTokenService {
    public boolean validate(String token) {
        String digits = token.replace("-", "");
        return isValidLuhn(digits);
    }

    private boolean isValidLuhn(String digits) {
        int nDigits = digits.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {

            int d = digits.charAt(i) - '0';

            if (isSecond) {
                d = d * 2;
            }

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}
