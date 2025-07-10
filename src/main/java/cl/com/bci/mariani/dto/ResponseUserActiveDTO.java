package cl.com.bci.mariani.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseUserActiveDTO extends UserDTO{

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
