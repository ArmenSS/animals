package com.animal.animalservice.service;

import com.animal.animalservice.dto.request.LoginRequestValidatorRequest;

import java.time.LocalDateTime;

public interface LoginRequestValidatorService {

    boolean valid(String userEmail, LocalDateTime localDateTime);

    void save(LoginRequestValidatorRequest loginRequestValidatorRequest);

    void delete(String UserEmail);

}
