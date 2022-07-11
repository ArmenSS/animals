package com.animal.animalservice.controller;

import com.animal.animalservice.dto.request.AnimalRequest;
import com.animal.animalservice.dto.response.AnimalResponse;
import com.animal.animalservice.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<HttpStatus> addAnimal(@RequestBody AnimalRequest animalRequest) {
        animalService.save(animalRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    @GetMapping("/myAnimals/{userId}")
    public ResponseEntity<List<AnimalResponse>> findMyAnimals(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(animalService.findMyAnimals(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable("id") Long id, @RequestBody AnimalRequest animalRequest) {
        return ResponseEntity.ok(animalService.update(id, animalRequest));
    }

    @DeleteMapping("/{animalId}/{userId}")
    public ResponseEntity<HttpStatus> deleteAnimal(@PathVariable("animalId") Long animalId,
                                                   @PathVariable("userId") Long userId) {
        animalService.delete(animalId, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
