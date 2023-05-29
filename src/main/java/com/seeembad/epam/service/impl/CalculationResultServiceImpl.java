package com.seeembad.epam.service.impl;

import com.seeembad.epam.domain.exception.ResourceNotFoundException;
import com.seeembad.epam.domain.result.CalculationsResult;
import com.seeembad.epam.repository.CalculationsResultRepository;
import com.seeembad.epam.service.CalculationResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationResultServiceImpl implements CalculationResultService {

    private final CalculationsResultRepository repository;

    @Override
    public CalculationsResult create(CalculationsResult calculationsResult) {
        return repository.save(calculationsResult);
    }

    @Override
    public List<CalculationsResult> createList(List<CalculationsResult> calculationsResults) {
        return repository.saveAll(calculationsResults);
    }

    @Override
    public CalculationsResult getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("can`t find this value"));
    }

    @Override
    public void delete(Long id) {

    }
}
