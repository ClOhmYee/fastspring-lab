package com.domain.spring.fastapi.client;

import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FastApiClientConfig {

    @Bean
    public FastApiServiceClient fastApiServiceClient(@Value("${fastapi.base-url:http://localhost:8000}") String baseUrl) {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
        
        return new FastApiServiceClient() {
            @Override
            public FastApiResponseDto process(FastApiRequestDto request) {
                return restClient.post()
                        .uri("/api/process")
                        .body(request)
                        .retrieve()
                        .body(FastApiResponseDto.class);
            }
        };
    }
}

