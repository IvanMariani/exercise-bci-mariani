package cl.com.bci.mariani.service;

import cl.com.bci.mariani.dto.PhoneDTO;
import cl.com.bci.mariani.dto.ResponseUserDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.entity.PhoneEntity;
import cl.com.bci.mariani.entity.UserEntity;
import cl.com.bci.mariani.exception.DuplicateUserException;
import cl.com.bci.mariani.exception.ValidMessageException;
import cl.com.bci.mariani.mapper.UserMapper;
import cl.com.bci.mariani.repository.UserRepository;
import cl.com.bci.mariani.service.impl.UserServiceImpl;
import cl.com.bci.mariani.util.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
public class UserServiceUTest {

    @InjectMocks
    UserServiceImpl userService;

    @Spy
    UserMapper mapper;

    @Mock
    UserRepository repository;

    @Spy
    JWTUtil jwtUtil;

    UserDTO user;

    UserEntity userEntity;

    @BeforeEach
    void init (){

        ReflectionTestUtils.setField(userService, "passwordRegex", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$");
        ReflectionTestUtils.setField(userService, "errorPassword", "Error");
        ReflectionTestUtils.setField(jwtUtil, "secret", "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf");



        user = UserDTO.builder()
                .name("Juan Rodriguez")
                .password("Tiag193!")
                .email("ivan@rodrigez.org")
                .phones(List.of(PhoneDTO.builder().number("1").cityCode("1").countryCode("1").build()))
                .build();

        userEntity = UserEntity.builder()
                .name("Juan Rodriguez")
                .password("Tiag193!")
                .email("ivan@rodrigez.org")
                .token("123")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .phones(List.of(PhoneEntity.builder().number("1").cityCode("1").countryCode("1").build()))
                .build();
    }

    @Test
    void testServiceOk(){
        Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(userEntity);
        assertThatCode(() -> userService.createUser(user)).doesNotThrowAnyException();
    }

    @Test
    void testServiceErrorPasswordFormat(){
        user.setPassword("a");
        assertThatCode(() -> userService.createUser(user)).isInstanceOf(ValidMessageException.class);
    }

    @Test
    void testServiceDuplicate(){
        Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(UserEntity.builder().build());
        assertThatCode(() -> userService.createUser(user)).isInstanceOf(DuplicateUserException.class);
    }

}
