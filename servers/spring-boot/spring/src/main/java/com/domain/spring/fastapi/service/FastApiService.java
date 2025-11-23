package com.domain.spring.fastapi.service;

import com.domain.spring.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class FastApiService {

    private static final String FASTAPI_URL = "http://localhost:8000/api/receive";
    private final RestTemplate restTemplate;

    public FastApiService() {
        this.restTemplate = new RestTemplate();
    }

    public MessageDto sendMessageToFastApi(String message) {
        log.info("FastAPI 서버({})로 메시지 전송: {}", FASTAPI_URL, message);

        MessageDto requestDto = new MessageDto(message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageDto> request = new HttpEntity<>(requestDto, headers);

        try {
            // FastAPI 서버로 POST 요청 전송
            MessageDto response = restTemplate.postForObject(
                    FASTAPI_URL,
                    request,
                    MessageDto.class
            );

            log.info("FastAPI 서버 응답 수신 완료: {}", response);
            return response;

        } catch (Exception e) {
            log.error("FastAPI 서버 통신 실패", e);
            throw new RuntimeException("FastAPI 서버와의 통신에 실패했습니다: " + e.getMessage(), e);
        }
    }
}

