package com.bichel.generator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class GeneratedToken extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long tokenDigitsSetId;
    @Column(name = "token")
    private String token;
}
