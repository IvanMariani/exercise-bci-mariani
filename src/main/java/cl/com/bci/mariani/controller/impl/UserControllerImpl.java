package cl.com.bci.mariani.controller.impl;

import cl.com.bci.mariani.controller.UserController;
import cl.com.bci.mariani.dto.ResponseUserActiveDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseUserActiveDTO> createUser(@RequestBody @Validated UserDTO userDTO){
        ResponseUserActiveDTO response = userService.createUser(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/login")
    public ResponseEntity<ResponseUserActiveDTO> getUser(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        ResponseUserActiveDTO response = userService.findUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
