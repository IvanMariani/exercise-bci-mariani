package cl.com.bci.mariani.errorhandler;

import cl.com.bci.mariani.exception.DuplicateUserException;
import cl.com.bci.mariani.exception.NotFoundException;
import cl.com.bci.mariani.exception.ValidMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RESTExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        return buildResponseEntity(new ListAPIError(List.of(new APIError(1,"Error no controlado"))),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<APIError> listError = new ArrayList<>();

        for(FieldError fe : ex.getBindingResult().getFieldErrors()){
            listError.add(new APIError(2,fe.getDefaultMessage()));
        }

        return buildResponseEntity(new ListAPIError(listError),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidMessageException.class)
    protected ResponseEntity<Object> validMessageException(ValidMessageException ex) {
        return buildResponseEntity(new ListAPIError(List.of(new APIError(3,ex.getErrorMessagePlaceholders()[0]))),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> notFoundException(NotFoundException ex) {
        return buildResponseEntity(new ListAPIError(List.of(new APIError(5,ex.getErrorMessagePlaceholders()[0]))),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUserException.class)
    protected ResponseEntity<Object> duplicateMessageException(DuplicateUserException ex) {
        return buildResponseEntity(new ListAPIError(List.of(new APIError(4,ex.getErrorMessagePlaceholders()[0]))),HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Object> buildResponseEntity(ListAPIError apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }


}