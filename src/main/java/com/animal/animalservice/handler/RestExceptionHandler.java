package com.animal.animalservice.handler;

import com.animal.animalservice.dto.response.ErrorDto;
import com.animal.animalservice.exception.IncorrectPasswordException;
import com.animal.animalservice.exception.NotValidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Random;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUnexpectedException(EntityNotFoundException e) {
        log.error("Entity was not found. Message: {}", e.getMessage());
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setNumber(new Random().nextInt());
        errorDTO.setText(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorDTO);
    }

    @ExceptionHandler(value = NotValidRequestException.class)
    public ResponseEntity<ErrorDto> handleUnexpectedException(NotValidRequestException e) {
        log.error("Entity was not found. Message: {}", e.getMessage());
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setNumber(new Random().nextInt());
        errorDTO.setText("you must repeat your login request after 1 hour");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorDTO);
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    public ResponseEntity<ErrorDto> handleUnexpectedException(IncorrectPasswordException e) {
        log.error("Entity was not found. Message: {}", e.getMessage());
        ErrorDto errorDTO = new ErrorDto();
        errorDTO.setNumber(new Random().nextInt());
        errorDTO.setText("incorrect password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorDTO);
    }

}
