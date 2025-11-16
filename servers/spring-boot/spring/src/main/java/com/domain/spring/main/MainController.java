package com.domain.spring.main;

import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;
import com.domain.spring.fastapi.service.FastApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
public class MainController {

    private final FastApiService fastApiService;

    public MainController(FastApiService fastApiService) {
        this.fastApiService = fastApiService;
    }

    @PostMapping("/send")
    public FastApiResponseDto sendToFastApi(@RequestBody FastApiRequestDto request) {
        return fastApiService.process(request);
    }
}
