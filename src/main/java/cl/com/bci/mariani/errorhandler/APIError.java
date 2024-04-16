package cl.com.bci.mariani.errorhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class APIError {

    private String message;

    public APIError() {
        this.message = "Unexpected error";
    }

    public APIError(String message) {
        this.message = message;
    }

}
