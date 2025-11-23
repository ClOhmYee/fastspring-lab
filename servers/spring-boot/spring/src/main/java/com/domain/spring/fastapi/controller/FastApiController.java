package com.domain.spring.fastapi.controller;

import com.domain.spring.dto.MessageDto;
import com.domain.spring.fastapi.service.FastApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FastApiController {

    private final FastApiService fastApiService;

    @GetMapping("/send")
    public ResponseEntity<MessageDto> sendToFastApi() {
        log.info("FastAPI 서버로 메시지 전송 시작");
        
        try {
            MessageDto response = fastApiService.sendMessageToFastApi("hello");
            log.info("FastAPI 서버 응답 수신: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("FastAPI 서버 통신 중 오류 발생", e);
            return ResponseEntity.internalServerError()
                    .body(new MessageDto("Error: " + e.getMessage()));
        }
    }
}

