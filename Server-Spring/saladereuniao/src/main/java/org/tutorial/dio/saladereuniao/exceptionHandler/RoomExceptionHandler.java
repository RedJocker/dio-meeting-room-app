package org.tutorial.dio.saladereuniao.exceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tutorial.dio.saladereuniao.exception.RoomNotFoundException;


import java.time.LocalTime;
import java.util.Map;

@ControllerAdvice
public class RoomExceptionHandler {
    private final String notBlankConstraint = "parameter cannot be null nor empty";
    private final Map<String, String> roomConstraints = Map.of(
    "name", "name " + notBlankConstraint,
    "date", "date " + notBlankConstraint,
    "startHour", "startHour " + notBlankConstraint,
    "endHour", "endHour " + notBlankConstraint
    );

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<?> notFound(RoomNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", e.getMessage(),
                        "timestamp", LocalTime.now().toString()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> badRequest(MethodArgumentNotValidException e) {

        final String errorMessage = roomConstraints.entrySet().stream()
                .filter(entry -> e.getMessage().contains("NotBlank.room." + entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("unidentified violation");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                    "error", errorMessage,
                    "timestamp", LocalTime.now().toString())
                );
    }
}
