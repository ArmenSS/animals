package com.animal.animalservice.repository;

import com.animal.animalservice.entity.LoginRequestValidator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginRequestValidatorRepository extends JpaRepository<LoginRequestValidator, Long> {

    List<LoginRequestValidator> findByUserEmailAndRequestTimeBetween(String userEmail, LocalDateTime start,LocalDateTime end);

    void deleteAllByUserEmail(String userEmail);
}
