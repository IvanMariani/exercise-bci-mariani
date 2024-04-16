package cl.com.bci.mariani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    @JsonProperty("name")
    String name;
    @JsonProperty("email")
    @Pattern(message = "Error in the email format. Example: aaaaaaa@dominio.cl)",regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
    String email;
    @JsonProperty("password")
    String password;
    @JsonProperty("phones")
    List<PhoneDTO> phones;
}
