package com.animal.animalservice.controller;

import com.animal.animalservice.dto.request.UserRequest;
import com.animal.animalservice.dto.response.LoginResponse;
import com.animal.animalservice.dto.response.UserResponse;
import com.animal.animalservice.exception.NotValidRequestException;
import com.animal.animalservice.service.LoginRequestValidatorService;
import com.animal.animalservice.service.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginRequestValidatorService validatorService;

    private static Bucket bucket;

    static {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofHours(1)));
        bucket = Bucket4j.builder().addLimit(limit).build();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registration(@RequestBody UserRequest userRequest) {
        userService.registration(userRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest loginRequest) {
        if (validatorService.valid(loginRequest.getEmail(), LocalDateTime.now())){
            return ResponseEntity.ok(userService.login(loginRequest));
        }
        throw new NotValidRequestException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
