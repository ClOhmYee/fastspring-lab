package com.domain.spring.fastapi.controller;

import com.domain.spring.fastapi.service.FastApiService;
import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fastapi")
public class FastApiController {

    private final FastApiService service;

    public FastApiController(FastApiService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public FastApiResponseDto callFastApi(@RequestBody FastApiRequestDto request) {
        return service.process(request);
    }
}
