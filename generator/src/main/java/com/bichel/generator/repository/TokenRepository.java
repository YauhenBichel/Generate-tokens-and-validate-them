package com.bichel.generator.repository;

import com.bichel.generator.entity.GeneratedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<GeneratedToken, Long> {
}
