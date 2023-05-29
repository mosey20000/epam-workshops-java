package com.seeembad.epam.service;

import com.seeembad.epam.domain.result.CalculationsResult;
import com.seeembad.epam.web.dto.AggregatingCalculationDTO;
import com.seeembad.epam.web.dto.RequestCalculationDTO;

import java.util.List;

public interface LogicService {
    CalculationsResult checkRequestedValue(RequestCalculationDTO dto);

    List<CalculationsResult> checkAllRequestValue(List<RequestCalculationDTO> dtoList);

    AggregatingCalculationDTO calculateAggregatingFunctionality(List<RequestCalculationDTO> dtoList);
}
