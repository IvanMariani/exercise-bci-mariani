package cl.com.bci.mariani.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "PHONES")
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity {

    @Id
    @Column(name = "PHONE_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "NUMBER")
    String number;
    @Column(name = "CITY_CODE")
    String cityCode;
    @Column(name = "COUNTRY_CODE")
    String countryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private UserEntity user;

}
