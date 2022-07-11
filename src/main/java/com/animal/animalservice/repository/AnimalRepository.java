package com.animal.animalservice.repository;

import com.animal.animalservice.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByUserId(Long userId);

}
