package bootcamp.java.mod6.linktracker.exception.handler;

import bootcamp.java.mod6.linktracker.endpoint.dto.ErrorDTO;
import bootcamp.java.mod6.linktracker.exception.InvalidCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidCredentialsExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDTO> handle(InvalidCredentialsException e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

}
