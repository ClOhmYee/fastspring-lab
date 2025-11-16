package com.domain.spring.fastapi;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.domain.spring.fastapi.client.FastApiClient;
import com.domain.spring.fastapi.service.FastApiService;
import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FastApiServiceTest {

    @Mock
    private FastApiClient fastApiClient;

    @InjectMocks
    private FastApiService fastApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess() {
        FastApiRequestDto request = new FastApiRequestDto();
        request.setMessage("hello");

        FastApiResponseDto response = new FastApiResponseDto();
        response.setResult("Received: hello");
        response.setStatus("ok");

        when(fastApiClient.sendRequest(request)).thenReturn(response);

        FastApiResponseDto result = fastApiService.process(request);

        assertEquals("Received: hello", result.getResult());
        assertEquals("ok", result.getStatus());

        verify(fastApiClient, times(1)).sendRequest(request);
    }
}
