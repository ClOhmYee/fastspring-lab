package com.domain.spring.fastapi.client;

import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FastApiClient {

    private final FastApiServiceClient client;

    public FastApiClient(FastApiServiceClient client) {
        this.client = client;
    }

    public FastApiResponseDto sendRequest(FastApiRequestDto request) {
        return client.process(request);
    }
}
