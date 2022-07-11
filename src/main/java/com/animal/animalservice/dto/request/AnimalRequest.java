package com.animal.animalservice.dto.request;

import com.animal.animalservice.entity.Gender;
import com.animal.animalservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequest {

    private LocalDate birthDay;
    private Gender gender;
    private String nickName;
    private Long currentUser;

}