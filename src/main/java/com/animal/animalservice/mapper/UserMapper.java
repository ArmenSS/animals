package com.animal.animalservice.mapper;

import com.animal.animalservice.dto.request.UserRequest;
import com.animal.animalservice.dto.response.UserResponse;
import com.animal.animalservice.entity.User;
import com.animal.animalservice.mapper.config.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<User, UserRequest, UserResponse> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    public User toEntity(UserResponse userResponse) {
        return modelMapper.map(userResponse, User.class);
    }

    @Override
    public UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }


}
