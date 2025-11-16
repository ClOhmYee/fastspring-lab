package com.domain.spring.fastapi.service;

import com.domain.spring.fastapi.client.FastApiClient;
import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;
import org.springframework.stereotype.Service;

@Service
public class FastApiService {

    private final FastApiClient client;

    public FastApiService(FastApiClient client) {
        this.client = client;
    }

    public FastApiResponseDto process(FastApiRequestDto request) {
        return client.sendRequest(request);
    }
}
