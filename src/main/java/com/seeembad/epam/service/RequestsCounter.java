package com.seeembad.epam.service;

public interface RequestsCounter {
    void requestAccepted();

    int getRequests();
}
