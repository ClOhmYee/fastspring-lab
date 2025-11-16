package com.domain.spring.fastapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FastApiResponseDto {

    private String result;
    private String status;

}
