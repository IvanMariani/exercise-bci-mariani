package cl.com.bci.mariani.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    String userId;
    @Column(name = "NAME")
    String name;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "PASSWORD")
    String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones = new ArrayList<>();
    @Column(name = "TOKEN")
    String token;
    @Column(name = "CREATED")
    LocalDateTime created = LocalDateTime.now();
    @Column(name = "MODIFIED")
    LocalDateTime modified = LocalDateTime.now();
    @Column(name = "LAST_LOGIN")
    LocalDateTime lastLogin = LocalDateTime.now();
    @Column(name = "IS_ACTIVE")
    Boolean isActive = Boolean.TRUE;

}
