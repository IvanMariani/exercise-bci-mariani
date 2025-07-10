package cl.com.bci.mariani.service.impl;

import cl.com.bci.mariani.dto.ResponseUserActiveDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.entity.UserEntity;
import cl.com.bci.mariani.exception.DuplicateUserException;
import cl.com.bci.mariani.exception.NotFoundException;
import cl.com.bci.mariani.exception.ValidMessageException;
import cl.com.bci.mariani.mapper.UserMapper;
import cl.com.bci.mariani.repository.UserRepository;
import cl.com.bci.mariani.service.UserService;
import cl.com.bci.mariani.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final JWTUtil jwtUtil;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, JWTUtil jwtUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.jwtUtil = jwtUtil;
    }

    @Value("${format.password.regex}")
    String passwordRegex;
    @Value("${format.password.error}")
    String errorPassword;

    @Override
    public ResponseUserActiveDTO createUser(UserDTO user) {

        if (!user.getPassword().matches(passwordRegex))
            throw ValidMessageException.builder()
                    .errorMessagePlaceholders(new String[]{errorPassword})
                    .build();


        if (repository.findByEmail(user.getEmail())!=null)
            throw DuplicateUserException.builder()
                    .errorMessagePlaceholders(new String[]{"El correo ya registrado"})
                    .build();


        UserEntity userEntity = mapper.toEntity(user);
        userEntity.setToken(jwtUtil.generateToken(user.getEmail()));

        userEntity = repository.save(userEntity);

        return mapper.makeResponse(userEntity);
    }

    @Override
    public ResponseUserActiveDTO findUser (String token){
        UserEntity user = repository.findByToken(token);

        if (user == null)
            throw NotFoundException.builder()
                    .errorMessagePlaceholders(new String[]{"User not found with that token"})
                    .build();

        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtUtil.generateToken(user.getEmail()));

        repository.save(user);

        return mapper.makeResponseUser(user);
    }
}
