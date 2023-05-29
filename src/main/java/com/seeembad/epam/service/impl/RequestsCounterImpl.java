package com.seeembad.epam.service.impl;

import com.seeembad.epam.service.RequestsCounter;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RequestsCounterImpl implements RequestsCounter {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void requestAccepted() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int getRequests() {
        return atomicInteger.get();
    }
}
