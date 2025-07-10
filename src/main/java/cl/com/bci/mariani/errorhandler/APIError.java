package cl.com.bci.mariani.errorhandler;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class APIError {

    private LocalDateTime timestamp;
    private Integer codigo;
    private String detail;

    public APIError() {
        this.detail = "Unexpected error";
    }

    public APIError(Integer codigo, String detail) {
        this.timestamp = LocalDateTime.now();
        this.codigo = codigo;
        this.detail = detail;
    }

}
