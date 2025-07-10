package cl.com.bci.mariani.service.impl

import cl.com.bci.mariani.dto.ResponseUserActiveDTO
import cl.com.bci.mariani.dto.UserDTO
import cl.com.bci.mariani.entity.UserEntity
import cl.com.bci.mariani.exception.DuplicateUserException
import cl.com.bci.mariani.exception.NotFoundException
import cl.com.bci.mariani.exception.ValidMessageException
import cl.com.bci.mariani.mapper.UserMapper
import cl.com.bci.mariani.repository.UserRepository
import cl.com.bci.mariani.util.JWTUtil
import spock.lang.Specification

class UserServiceImplSpec extends Specification {

    UserRepository repository = Mock()
    UserMapper mapper = Mock()
    JWTUtil jwtUtil = Mock()

    UserServiceImpl userService

    def setup() {
        userService = new UserServiceImpl(repository, mapper, jwtUtil)
        // Inyectar valores @Value manualmente
        userService.passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{6,}\$" // ejemplo
        userService.errorPassword = "Invalid password format"
    }

    def "createUser - lanza ValidMessageException si password no cumple regex"() {
        given:
        UserDTO user = new UserDTO(email: "test@test.com", password: "badpass")

        when:
        userService.createUser(user)

        then:
        def e = thrown(ValidMessageException)
        e.errorMessagePlaceholders[0] == "Invalid password format"
    }

    def "createUser - lanza DuplicateUserException si email ya existe"() {
        given:
        UserDTO user = new UserDTO(email: "test@test.com", password: "GoodPass1")
        repository.findByEmail("test@test.com") >> new UserEntity()

        when:
        userService.createUser(user)

        then:
        def e = thrown(DuplicateUserException)
        e.errorMessagePlaceholders[0] == "El correo ya registrado"
    }

    def "createUser - guarda usuario y retorna ResponseUserActiveDTO"() {
        given:
        UserDTO user = new UserDTO(email: "test@test.com", password: "GoodPass1")
        repository.findByEmail("test@test.com") >> null

        UserEntity userEntity = new UserEntity(email: "test@test.com")
        UserEntity savedEntity = new UserEntity(email: "test@test.com", token: "token123")

        mapper.toEntity(user) >> userEntity
        jwtUtil.generateToken("test@test.com") >> "token123"
        repository.save(userEntity) >> savedEntity
        mapper.makeResponse(savedEntity) >> ResponseUserActiveDTO.builder().build();

        when:
        ResponseUserActiveDTO response = userService.createUser(user)

        then:
        response != null
    }

    def "findUser - lanza ValidMessageException si token no existe"() {
        given:
        repository.findByToken("token123") >> null

        when:
        userService.findUser("token123")

        then:
        def e = thrown(NotFoundException)
        e.errorMessagePlaceholders[0] == "User not found with that token"
    }

    def "findUser - actualiza lastLogin, token y retorna ResponseUserActiveDTO"() {
        given:
        UserEntity userEntity = new UserEntity(email: "test@test.com", token: "oldtoken")
        repository.findByToken("token123") >> userEntity
        jwtUtil.generateToken("test@test.com") >> "newtoken"
        repository.save(_) >> { args -> args[0] }
        mapper.makeResponseUser(_) >> ResponseUserActiveDTO.builder().build();

        when:
        ResponseUserActiveDTO response = userService.findUser("token123")

        then:
        response != null
    }
}