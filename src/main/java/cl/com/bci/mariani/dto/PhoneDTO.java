package cl.com.bci.mariani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDTO {

    @JsonProperty("number")
    Long number;
    @JsonProperty("citycode")
    Integer cityCode;
    @JsonProperty("contrycode")
    String countryCode;

}
