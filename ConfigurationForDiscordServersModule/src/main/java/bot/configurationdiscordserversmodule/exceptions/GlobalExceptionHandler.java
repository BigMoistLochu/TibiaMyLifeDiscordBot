package bot.configurationdiscordserversmodule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> ServerAlreadyExistsHandler(final ServerAlreadyExistsException ex){
        LocalDateTime actualTime = LocalDateTime.now();
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(),actualTime));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> IllegalArgumentExceptionHandler(final IllegalArgumentException ex){
        LocalDateTime actualTime = LocalDateTime.now();
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(),actualTime));
    }









}
