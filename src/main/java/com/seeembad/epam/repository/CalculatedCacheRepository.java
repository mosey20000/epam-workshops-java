package com.seeembad.epam.repository;

import com.seeembad.epam.domain.result.CalculationsResult;
import com.seeembad.epam.web.dto.RequestCalculationDTO;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CalculatedCacheRepository {
    private final Map<RequestCalculationDTO, CalculationsResult> cacheMap = new ConcurrentHashMap<>();

    public boolean isContainsValue(RequestCalculationDTO dto) {
        return cacheMap.containsKey(dto);
    }

    public void cacheNewValue(RequestCalculationDTO dto, CalculationsResult result) {
        cacheMap.put(dto, result);
    }

    public CalculationsResult getFromCache(RequestCalculationDTO dto) {
        return cacheMap.get(dto);
    }
}
