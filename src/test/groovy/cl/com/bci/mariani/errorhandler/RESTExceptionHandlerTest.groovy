package cl.com.bci.mariani.errorhandler

import cl.com.bci.mariani.dto.UserDTO
import cl.com.bci.mariani.exception.DuplicateUserException
import cl.com.bci.mariani.exception.NotFoundException
import cl.com.bci.mariani.exception.ValidMessageException
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import spock.lang.Specification

class RESTExceptionHandlerTest extends Specification {

    def handler = new RESTExceptionHandler()

    def "HandleException"() {
        given:
        def ex = new RuntimeException("Algo salió mal")

        when:
        ResponseEntity<Object> response = handler.handleException(ex)

        then:
        response.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
        def error = response.body as ListAPIError
        error.error[0].codigo == 1
        error.error[0].detail == "Error no controlado"
    }

    def "MethodArgumentNotValidException"() {
        given:
        def user = new UserDTO()
        def bindingResult = new BeanPropertyBindingResult(user, "userDTO")
        bindingResult.rejectValue("email", "error.email", "Email inválido")
        bindingResult.rejectValue("password", "error.password", "Password requerido")

        // Método simulado para pasar como parámetro
        MethodParameter methodParam = Mock()

        def exception = new MethodArgumentNotValidException(methodParam, bindingResult)

        when:
        def response = handler.methodArgumentNotValidException(exception)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        def error = response.body as ListAPIError
        error.error.size() == 2
        error.error*.codigo == [2, 2]
        error.error*.detail.contains("Email inválido")
        error.error*.detail.contains("Password requerido")
    }

    def "ValidMessageException"() {
        given:
        def ex = ValidMessageException.builder()
                .errorMessagePlaceholders(new String[]{"mensaje válido"})
                .build();

        when:
        def response = handler.validMessageException(ex)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        def error = response.body as ListAPIError
        error.error[0].codigo == 3
        error.error[0].detail == "mensaje válido"
    }

    def "NotFoundException"() {
        given:
        def ex = NotFoundException.builder()
                .errorMessagePlaceholders(new String[]{"user not found"})
                .build();

        when:
        def response = handler.notFoundException(ex)

        then:
        response.statusCode == HttpStatus.NOT_FOUND
        def error = response.body as ListAPIError
        error.error[0].codigo == 5
        error.error[0].detail == "user not found"
    }

    def "DuplicateUserException"() {
        given:
        def ex = DuplicateUserException.builder()
                .errorMessagePlaceholders(new String[]{"duplicado"})
                .build();

        when:
        def response = handler.duplicateMessageException(ex)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        def error = response.body as ListAPIError
        error.error[0].codigo == 4
        error.error[0].detail == "duplicado"
    }
}
