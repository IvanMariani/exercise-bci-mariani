package cl.com.bci.mariani.controller;

import cl.com.bci.mariani.controller.impl.UserControllerImpl;
import cl.com.bci.mariani.dto.ResponseUserDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
public class UserControllerUTest {

    @InjectMocks
    UserControllerImpl userController;

    @Mock
    UserService userService;

    UserDTO user;

    @BeforeEach
    void init (){
        user = UserDTO.builder().build();
    }

    @Test
    void testController(){
        Mockito.when(userService.createUser(user)).thenReturn(ResponseUserDTO.builder().build());
        assertThatCode(() -> userController.createUser(user)).doesNotThrowAnyException();
    }
}
