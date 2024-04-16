package cl.com.bci.mariani.controller.impl;

import cl.com.bci.mariani.controller.UserController;
import cl.com.bci.mariani.dto.ResponseUserDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/users")
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody @Validated UserDTO userDTO){
        ResponseUserDTO response = userService.createUser(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
