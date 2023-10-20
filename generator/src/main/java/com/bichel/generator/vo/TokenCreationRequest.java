package com.bichel.generator.vo;

import lombok.Data;

import java.util.List;

@Data
public class TokenCreationRequest {
    private List<Integer> digits;
}
