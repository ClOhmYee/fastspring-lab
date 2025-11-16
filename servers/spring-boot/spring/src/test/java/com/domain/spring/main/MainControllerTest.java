package com.domain.spring.main;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.domain.spring.fastapi.dto.FastApiRequestDto;
import com.domain.spring.fastapi.dto.FastApiResponseDto;
import com.domain.spring.fastapi.service.FastApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FastApiService fastApiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSendToFastApi() throws Exception {
        FastApiRequestDto request = new FastApiRequestDto();
        request.setMessage("hello");

        FastApiResponseDto response = new FastApiResponseDto();
        response.setResult("Received: hello");
        response.setStatus("ok");

        when(fastApiService.process(any(FastApiRequestDto.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/main/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("Received: hello"))
                .andExpect(jsonPath("$.status").value("ok"));

        verify(fastApiService, times(1)).process(any(FastApiRequestDto.class));
    }
}
