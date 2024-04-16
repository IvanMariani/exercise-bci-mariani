package cl.com.bci.mariani.errorhandler;

import cl.com.bci.mariani.exception.DuplicateUserException;
import cl.com.bci.mariani.exception.ValidMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@ControllerAdvice
public class RESTExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        return buildResponseEntity(new APIError("Error no controlado"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return buildResponseEntity(new APIError( ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidMessageException.class)
    protected ResponseEntity<Object> validMessageException(ValidMessageException ex) {
        return buildResponseEntity(new APIError(Arrays.toString(ex.getErrorMessagePlaceholders())),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateUserException.class)
    protected ResponseEntity<Object> duplicateMessageException(DuplicateUserException ex) {
        return buildResponseEntity(new APIError(Arrays.toString(ex.getErrorMessagePlaceholders())),HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Object> buildResponseEntity(APIError apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }


}