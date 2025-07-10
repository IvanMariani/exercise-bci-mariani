package cl.com.bci.mariani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("name")
    String name;
    @JsonProperty("email")
    @Pattern(message = "Error in the email format. Example: aaaaaaa@dominio.cl)",regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
    String email;
    @JsonProperty("password")
    @NotEmpty(message = "Error password must be not empty")
    String password;
    @JsonProperty("phones")
    List<PhoneDTO> phones;
}
