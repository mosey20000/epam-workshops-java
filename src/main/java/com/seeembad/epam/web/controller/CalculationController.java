package com.seeembad.epam.web.controller;


import com.seeembad.epam.domain.result.CalculationsResult;
import com.seeembad.epam.service.CalculationResultService;
import com.seeembad.epam.service.LogicService;
import com.seeembad.epam.service.RequestsCounter;
import com.seeembad.epam.web.dto.AggregatingCalculationDTO;
import com.seeembad.epam.web.dto.RequestCalculationDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calculation/")
public class CalculationController {

    private final LogicService logicService;
    private final CalculationResultService calculationResultService;
    private final RequestsCounter requestsCounter;
    private final Logger logger = LogManager.getLogger();

    @PostMapping
    public CalculationsResult create(@RequestBody @Validated RequestCalculationDTO requestCalculationDTO) {
        logger.info("request accepted from: " + this.getClass());
        requestsCounter.requestAccepted();
        return logicService.checkRequestedValue(requestCalculationDTO);
    }

    @GetMapping("/{id}/")
    public CalculationsResult get(@PathVariable long id) {
        requestsCounter.requestAccepted();
        return calculationResultService.getById(id);
    }

    @PostMapping("/all/")
    public List<CalculationsResult> createBulk(@RequestBody @Validated List<RequestCalculationDTO> requestCalculationDTOS) {
        logger.info("bulk request accepted from " + this.getClass());
        requestsCounter.requestAccepted();
        return logicService.checkAllRequestValue(requestCalculationDTOS);
    }

    @GetMapping
    public AggregatingCalculationDTO aggregatingCalculationDTO(@RequestBody @Validated List<RequestCalculationDTO> requestCalculationDTOS) {
        logger.info("aggregating functionality request");
        requestsCounter.requestAccepted();
        return logicService.calculateAggregatingFunctionality(requestCalculationDTOS);
    }

    @PostMapping("/future/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void calculateAsync(@RequestBody @Validated List<RequestCalculationDTO> requestCalculationDTOS) {
        CompletableFuture.runAsync(() -> logicService.checkAllRequestValue(requestCalculationDTOS));
    }
}
