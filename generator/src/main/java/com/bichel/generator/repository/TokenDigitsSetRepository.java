package com.bichel.generator.repository;

import com.bichel.generator.entity.TokenDigitsSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDigitsSetRepository extends JpaRepository<TokenDigitsSet, Long> {
}
