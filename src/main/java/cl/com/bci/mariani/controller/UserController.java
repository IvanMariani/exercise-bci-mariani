package cl.com.bci.mariani.controller;

import cl.com.bci.mariani.dto.ResponseUserDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.errorhandler.APIError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Tag(name = "User", description = "The User Api")
public interface UserController {


    @Operation(
            summary = "Create a user",
            description = "Creation of the user in the database")
    @RequestBody(description = "Details of the User",
    required = true,
    content = @Content(
            schema = @Schema(implementation = UserDTO.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = {
                    @ExampleObject (
                            name = "Example",
                            value = "{\n" + "    \"name\": \"Juan Rodriguez\",\n" + "    \"email\": \"ivan@rodrigez.org\",\n" + "    \"password\": \"Tiag193!\",\n" + "    \"phones\": [\n" + "        {\n" + "            \"number\": \"1234567\",\n" + "            \"citycode\": \"1\",\n" + "            \"contrycode\": \"57\"\n" + "        },\n" + "        {\n" + "            \"number\": \"12341\",\n" + "            \"citycode\": \"1\",\n" + "            \"contrycode\": \"57\"\n" + "        }\n" + "    ]\n" + "}"
                    )
            }
    ))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseUserDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = APIError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = APIError.class))
            })
    })
    ResponseEntity<ResponseUserDTO> createUser(UserDTO userDTO);
}
