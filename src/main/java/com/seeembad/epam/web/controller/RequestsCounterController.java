package com.seeembad.epam.web.controller;

import com.seeembad.epam.service.RequestsCounter;
import com.seeembad.epam.web.dto.RequestsAcceptedDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/counter/")
public class RequestsCounterController {

    private final Logger logger = LogManager.getLogger();
    private final RequestsCounter requestsCounter;

    @GetMapping("/accepted/")
    public RequestsAcceptedDTO RequestCounter() {
        logger.info("get counter");
        return new RequestsAcceptedDTO(requestsCounter.getRequests());
    }
}
