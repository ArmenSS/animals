package com.animal.animalservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestValidatorRequest {

    private LocalDateTime requestTime;
    private String userEmail;

}
