package com.seeembad.epam.service.impl;

import com.seeembad.epam.domain.result.CalculationsResult;
import com.seeembad.epam.repository.CalculatedCacheRepository;
import com.seeembad.epam.service.CalculationResultService;
import com.seeembad.epam.service.LogicService;
import com.seeembad.epam.web.dto.AggregatingCalculationDTO;
import com.seeembad.epam.web.dto.RequestCalculationDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogicServiceImpl implements LogicService {

    Logger logger = LogManager.getLogger();
    CalculatedCacheRepository cacheRepository;
    CalculationResultService calculationResultService;

    @Override
    public CalculationsResult checkRequestedValue(RequestCalculationDTO dto) {
        if (cacheRepository.isContainsValue(dto)) {
            logger.info("result for request get from cache");
            return cacheRepository.getFromCache(dto);
        }

        boolean isEven = dto.getValue() % 2 == 0;

        BigInteger value = BigInteger.valueOf(dto.getValue());
        boolean isPrime = value.isProbablePrime((int) Math.log(dto.getValue()));

        CalculationsResult result = new CalculationsResult(dto.getValue(), isEven, isPrime);

        cacheRepository.cacheNewValue(dto, result);
        calculationResultService.create(result);
        return result;
    }

    @Override
    public List<CalculationsResult> checkAllRequestValue(List<RequestCalculationDTO> dtoList) {
        List<CalculationsResult> resultDTOS = new ArrayList<>();
        for (RequestCalculationDTO dto: dtoList) {
            resultDTOS.add(this.checkRequestedValue(dto));
        }

        return resultDTOS;
    }

    @Override
    public AggregatingCalculationDTO calculateAggregatingFunctionality(List<RequestCalculationDTO> dtoList) {
        int total = dtoList.stream()
                .mapToInt(RequestCalculationDTO::getValue)
                .sum();

        int min = dtoList.stream()
                .mapToInt(RequestCalculationDTO::getValue)
                .min()
                .orElse(0);

        int max = dtoList.stream()
                .mapToInt(RequestCalculationDTO::getValue)
                .max()
                .orElse(0);

        return new AggregatingCalculationDTO(max, min, (int) total / dtoList.size());
    }
}
