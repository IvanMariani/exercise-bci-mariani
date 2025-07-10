package cl.com.bci.mariani.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "PHONES")
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity {

    @Id
    @Column(name = "PHONE_ID")
    String id;
    @Column(name = "NUMBER")
    Long number;
    @Column(name = "CITY_CODE")
    Integer cityCode;
    @Column(name = "COUNTRY_CODE")
    String countryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private UserEntity user;

}
