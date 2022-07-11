package com.animal.animalservice.service.impl;

import com.animal.animalservice.dto.request.AnimalRequest;
import com.animal.animalservice.dto.response.AnimalResponse;
import com.animal.animalservice.entity.Animal;
import com.animal.animalservice.mapper.AnimalMapper;
import com.animal.animalservice.repository.AnimalRepository;
import com.animal.animalservice.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<AnimalResponse> findMyAnimals(Long userId) {
        return animalRepository.findAllByUserId(userId)
                .stream()
                .map(animalMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AnimalResponse findById(Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return animalMapper.toResponse(animal);
    }

    @Override
    public AnimalResponse save(AnimalRequest animalRequest) {
        Animal animal = animalRepository.save(animalMapper.toEntity(animalRequest));
        return animalMapper.toResponse(animal);
    }

    @Transactional
    @Override
    public AnimalResponse update(Long id, AnimalRequest animalRequest) {
        Animal animal = animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (animal.getUser().getId().equals(animalRequest.getCurrentUser())) {
            modelMapper.map(animal, animalRequest);
        }
        return animalMapper.toResponse(animal);
    }

    @Override
    public void delete(Long animalId, Long userId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(EntityNotFoundException::new);
        if (animal.getUser().getId().equals(userId)) {
            animalRepository.delete(animal);
        }
    }
}
