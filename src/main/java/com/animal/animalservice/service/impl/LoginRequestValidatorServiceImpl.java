package com.animal.animalservice.service.impl;

import com.animal.animalservice.dto.request.LoginRequestValidatorRequest;
import com.animal.animalservice.entity.LoginRequestValidator;
import com.animal.animalservice.repository.LoginRequestValidatorRepository;
import com.animal.animalservice.service.LoginRequestValidatorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginRequestValidatorServiceImpl implements LoginRequestValidatorService {

    private final LoginRequestValidatorRepository repository;
    private final ModelMapper modelMapper;


    @Override
    public boolean valid(String userEmail, LocalDateTime localDateTime) {
        List<LoginRequestValidator> validatorList =
                repository.findByUserEmailAndRequestTimeBetween(userEmail, localDateTime.minusHours(1L),localDateTime);
        if (validatorList.size() == 10) {
            return false;
        }
        return true;
    }

    @Override
    public void save(LoginRequestValidatorRequest loginRequestValidatorRequest) {
        LoginRequestValidator requestValidator = modelMapper.map(loginRequestValidatorRequest, LoginRequestValidator.class);
        repository.save(requestValidator);
    }

    @Override
    @Transactional
    public void delete(String userEmail) {
        repository.deleteAllByUserEmail(userEmail);
    }
}
