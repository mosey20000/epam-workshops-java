package com.seeembad.epam.repository;

import com.seeembad.epam.domain.result.CalculationsResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationsResultRepository extends JpaRepository<CalculationsResult, Long> {
}
