package com.animal.animalservice.service;

import com.animal.animalservice.dto.request.UserRequest;
import com.animal.animalservice.dto.response.LoginResponse;
import com.animal.animalservice.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse findByEmail(String email);

    void registration(UserRequest userRequest);

     LoginResponse login (UserRequest loginRequest);

    UserResponse findById(Long id);

    void deleteById(Long id);

    List<UserResponse> findAll();

}
