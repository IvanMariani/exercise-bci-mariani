package cl.com.bci.mariani.mapper;

import cl.com.bci.mariani.dto.PhoneDTO;
import cl.com.bci.mariani.dto.ResponseUserActiveDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.entity.PhoneEntity;
import cl.com.bci.mariani.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDTO userDTO){
        List<PhoneEntity> listPhones = new ArrayList<>();


        UserEntity entity = UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .build();

        if (userDTO.getPhones() != null){
            userDTO.getPhones().forEach(p ->{
                listPhones.add(toPhoneEntity(p,entity));
            });
        }

        entity.setPhones(listPhones);

        return entity ;
    }

    PhoneEntity toPhoneEntity (PhoneDTO phoneDTO, UserEntity userEntity){
        return PhoneEntity.builder()
                .id(UUID.randomUUID().toString())
                .cityCode(phoneDTO.getCityCode())
                .number(phoneDTO.getNumber())
                .countryCode(phoneDTO.getCountryCode())
                .user(userEntity)
                .build();
    }

    PhoneDTO toPhoneDto (PhoneEntity phoneEntity){
        return PhoneDTO.builder()
                .cityCode(phoneEntity.getCityCode())
                .number(phoneEntity.getNumber())
                .countryCode(phoneEntity.getCountryCode())
                .build();
    }

    public ResponseUserActiveDTO makeResponse (UserEntity user){

        return ResponseUserActiveDTO.builder()
                .id(user.getUserId())
                .creaded(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isactive(user.getIsActive())
                .build();

    }

    public ResponseUserActiveDTO makeResponseUser(UserEntity user){

        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        if (!user.getPhones().isEmpty()){
            user.getPhones().forEach(p ->{
                phoneDTOS.add(toPhoneDto(p));
            });
        }

        return ResponseUserActiveDTO.builder()
                .id(user.getUserId())
                .creaded(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isactive(user.getIsActive())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(phoneDTOS)
                .build();
    }
}
