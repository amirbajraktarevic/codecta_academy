package ba.codecta.springlearning.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@ControllerAdvice
public class SpringController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception exception) {
        log.error(exception.getMessage(), exception);
        var caseMessage = "";
        var exceptionCase = exception.getCause();
        while (!isEmpty(exceptionCase)) {
            caseMessage = exception.getCause().getMessage() + ", ";
            exceptionCase = exceptionCase.getCause();
        }
        return new ResponseEntity<>(List.of(exception.getMessage(), caseMessage), HttpStatus.BAD_REQUEST);
    }
}
