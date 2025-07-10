package cl.com.bci.mariani.controller.impl

import cl.com.bci.mariani.dto.ResponseUserActiveDTO
import cl.com.bci.mariani.dto.UserDTO
import cl.com.bci.mariani.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class UserControllerImplTest extends Specification {

    def userService = Mock(UserService)
    def controller = new UserControllerImpl(userService)

    def "CreateUser"() {
        given:
        def userDTO = new UserDTO(email: "test@mail.com", password: "Pass123", name: "Ivan")
        def responseDTO = ResponseUserActiveDTO.builder().build();
        userService.createUser(userDTO) >> responseDTO

        when:
        ResponseEntity<ResponseUserActiveDTO> response = controller.createUser(userDTO)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body == responseDTO
    }

    def "GetUser"() {
        given:
        def token = "abc.123.token"
        def header = "Bearer $token"
        def responseDTO = ResponseUserActiveDTO.builder().build();
        userService.findUser(token) >> responseDTO

        when:
        ResponseEntity<ResponseUserActiveDTO> response = controller.getUser(header)

        then:
        response.statusCode == HttpStatus.OK
        response.body == responseDTO
    }
}
