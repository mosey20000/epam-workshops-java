package com.seeembad.epam.service;

import com.seeembad.epam.domain.result.CalculationsResult;

import java.util.List;

public interface CalculationResultService {

    CalculationsResult create(CalculationsResult calculationResultDTO);

    List<CalculationsResult> createList(List<CalculationsResult> calculationResultDTOList);

    CalculationsResult getById(Long id);

    void delete(Long id);
}
