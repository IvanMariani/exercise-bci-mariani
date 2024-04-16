package cl.com.bci.mariani.repository;

import cl.com.bci.mariani.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);
}
