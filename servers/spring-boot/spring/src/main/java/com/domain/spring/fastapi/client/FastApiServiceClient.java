package com.domain.spring.fastapi.client;

import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;

public interface FastApiServiceClient {
    FastApiResponseDto process(FastApiRequestDto request);
}

