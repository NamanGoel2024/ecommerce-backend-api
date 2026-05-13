package ecommerce_backend_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<Map<String, Object>>
    handleResourceNotFound(ResourceNotFoundException ex) {

        Map<String, Object> error =
                new HashMap<>();

        error.put("message", ex.getMessage());

        error.put("status", 404);

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<Map<String, Object>>
    handleGeneralException(Exception ex) {

        Map<String, Object> error =
                new HashMap<>();

        error.put("message", ex.getMessage());

        error.put("status", 500);

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}