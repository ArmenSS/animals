package com.animal.animalservice.service;

import com.animal.animalservice.dto.request.AnimalRequest;
import com.animal.animalservice.dto.response.AnimalResponse;

import java.util.List;

public interface AnimalService {

    List<AnimalResponse> findMyAnimals(Long userId);

    AnimalResponse findById(Long id);

    AnimalResponse save(AnimalRequest animalRequest);

    AnimalResponse update(Long id,AnimalRequest animalRequest);

    void delete(Long animalId, Long userId);

}
