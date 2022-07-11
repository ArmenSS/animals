package com.animal.animalservice.dto.response;

import com.animal.animalservice.entity.Gender;
import com.animal.animalservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private Long id;
    private LocalDate birthDay;
    private Gender gender;
    private String nickName;
    private User user;

}