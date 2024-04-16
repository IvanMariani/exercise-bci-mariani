package cl.com.bci.mariani.mapper;

import cl.com.bci.mariani.dto.PhoneDTO;
import cl.com.bci.mariani.dto.ResponseUserDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.entity.PhoneEntity;
import cl.com.bci.mariani.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDTO userDTO){
        List<PhoneEntity> listPhones = new ArrayList<>();


        UserEntity entity = UserEntity.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .build();

        userDTO.getPhones().forEach(p ->{
            listPhones.add(toPhoneEntity(p,entity));
        });

        entity.setPhones(listPhones);

        return entity ;
    }

    PhoneEntity toPhoneEntity (PhoneDTO phoneDTO, UserEntity userEntity){
        return PhoneEntity.builder()
                .cityCode(phoneDTO.getCityCode())
                .number(phoneDTO.getNumber())
                .countryCode(phoneDTO.getCountryCode())
                .user(userEntity)
                .build();
    }

    public ResponseUserDTO makeResponse (UserEntity user){

        return ResponseUserDTO.builder()
                .id(user.getUserId())
                .creaded(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isactive(user.getIsActive())
                .build();

    }
}
