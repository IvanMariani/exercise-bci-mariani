package cl.com.bci.mariani.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "USER_ID")
    String userId;
    @Column(name = "NAME")
    String name;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "PASSWORD")
    String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PhoneEntity> phones = new ArrayList<>();
    @Column(name = "TOKEN")
    String token;
    @Column(name = "CREATED")
    @Builder.Default
    LocalDateTime created = LocalDateTime.now();
    @Column(name = "MODIFIED")
    @Builder.Default
    LocalDateTime modified = LocalDateTime.now();
    @Column(name = "LAST_LOGIN")
    @Builder.Default
    LocalDateTime lastLogin = LocalDateTime.now();
    @Column(name = "IS_ACTIVE")
    @Builder.Default
    Boolean isActive = Boolean.TRUE;

}
