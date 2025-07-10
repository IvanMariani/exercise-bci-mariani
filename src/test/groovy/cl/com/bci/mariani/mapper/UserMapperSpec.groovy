package cl.com.bci.mariani.mapper

import cl.com.bci.mariani.dto.PhoneDTO
import cl.com.bci.mariani.dto.UserDTO
import cl.com.bci.mariani.entity.PhoneEntity
import cl.com.bci.mariani.entity.UserEntity
import spock.lang.Specification

import java.time.LocalDateTime

class UserMapperSpec extends Specification {

    def mapper = new UserMapper()

    def "toEntity mapea campos correctamente y genera userId"() {
        given:
        def phone = PhoneDTO.builder().cityCode(1).number(123L).countryCode("54").build()
        def userDTO = UserDTO.builder()
                .email("test@mail.com")
                .password("pass")
                .name("ivan")
                .phones(List.of(phone)).build();

        when:
        def entity = mapper.toEntity(userDTO)

        then:
        entity.email == userDTO.email
        entity.name == userDTO.name
        entity.password == userDTO.password
        entity.phones.size() == 1
        entity.phones[0].number == 123L
        entity.userId != null
        entity.created != null
        entity.modified != null
        entity.lastLogin != null
        entity.isActive
    }

    def "makeResponse crea DTO básico desde entidad"() {
        given:
        def entity = UserEntity.builder()
                .userId("uuid-123")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("token123")
                .isActive(true)
                .build()

        when:
        def dto = mapper.makeResponse(entity)

        then:
        dto.id == entity.userId
        dto.token == entity.token
        dto.isactive
    }

    def "makeResponseUser incluye datos personales y teléfonos"() {
        given:
        def phone = PhoneEntity.builder()
                .number(123L)
                .cityCode(1)
                .countryCode("54")
                .build()

        def entity = UserEntity.builder()
                .userId("uuid-123")
                .email("mail@mail.com")
                .name("Ivan")
                .password("pass")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("token")
                .isActive(true)
                .phones([phone])
                .build()

        when:
        def dto = mapper.makeResponseUser(entity)

        then:
        dto.email == entity.email
        dto.name == entity.name
        dto.token == entity.token
        dto.phones.size() == 1
        dto.phones[0].number == 123L
    }
}
