package com.animal.animalservice.mapper;

import com.animal.animalservice.dto.request.AnimalRequest;
import com.animal.animalservice.dto.response.AnimalResponse;
import com.animal.animalservice.entity.Animal;
import com.animal.animalservice.mapper.config.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalMapper implements BaseMapper<Animal, AnimalRequest, AnimalResponse> {

    private final ModelMapper modelMapper;

    @Override
    public Animal toEntity(AnimalRequest animalRequest) {
        return modelMapper.map(animalRequest, Animal.class);
    }

    @Override
    public AnimalResponse toResponse(Animal animal) {
        return modelMapper.map(animal, AnimalResponse.class);
    }


}
