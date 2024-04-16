package cl.com.bci.mariani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseUserDTO {

    @JsonProperty("id")
    String id;
    @JsonProperty("creaded")
    LocalDateTime creaded;
    @JsonProperty("modified")
    LocalDateTime modified;
    @JsonProperty("last_login")
    LocalDateTime lastLogin;
    @JsonProperty("token")
    String token;
    @JsonProperty("isactive")
    Boolean isactive;
}
