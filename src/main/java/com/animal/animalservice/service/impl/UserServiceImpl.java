package com.animal.animalservice.service.impl;

import com.animal.animalservice.dto.request.LoginRequestValidatorRequest;
import com.animal.animalservice.dto.request.UserRequest;
import com.animal.animalservice.dto.response.LoginResponse;
import com.animal.animalservice.dto.response.UserResponse;
import com.animal.animalservice.entity.User;
import com.animal.animalservice.exception.EmailExistException;
import com.animal.animalservice.exception.IncorrectPasswordException;
import com.animal.animalservice.mapper.UserMapper;
import com.animal.animalservice.repository.UserRepository;
import com.animal.animalservice.security.JwtTokenUtil;
import com.animal.animalservice.service.LoginRequestValidatorService;
import com.animal.animalservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginRequestValidatorService validatorService;

    @Value("${token.expiration}")
    private Long expiration;

    @Override
    public UserResponse findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        return userMapper.toResponse(user.get());
    }

    @Override
    public void registration(UserRequest userRequest) {
        Optional<User> userByEmail = userRepository.findByEmail(userRequest.getEmail());
        if (userByEmail.isPresent()) {
            throw new EmailExistException();
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(userMapper.toEntity(userRequest));
    }

    @Override
    public LoginResponse login(UserRequest loginRequest) {
        UserResponse userResponse = findByEmail(loginRequest.getEmail());
        if (userResponse == null) {
            validatorService.save(new LoginRequestValidatorRequest(LocalDateTime.now(), loginRequest.getEmail()));
            throw new EntityNotFoundException();
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), userResponse.getPassword())) {
            validatorService.save(new LoginRequestValidatorRequest(LocalDateTime.now(), loginRequest.getEmail()));
            throw new IncorrectPasswordException();
        }
        validatorService.delete(loginRequest.getEmail());
        return new LoginResponse(
                userResponse, jwtTokenUtil.generateToken(userResponse, expiration)
        );
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.toResponse(user.orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
}
