package cl.com.bci.mariani.controller;

import cl.com.bci.mariani.dto.ResponseUserActiveDTO;
import cl.com.bci.mariani.dto.ResponseUserDTO;
import cl.com.bci.mariani.dto.UserDTO;
import cl.com.bci.mariani.errorhandler.APIError;
import cl.com.bci.mariani.errorhandler.ListAPIError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ListAPIError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ListAPIError.class))
            })
    })
    ResponseEntity<ResponseUserActiveDTO> createUser(UserDTO userDTO);

    @Operation(
            summary = "Get user info by token",
            description = "Obtains user information based on the Authorization token"
    )
    @Parameters({
            @Parameter(
                    name = "Authorization",
                    description = "JWT token in format 'Bearer {token}'",
                    required = true,
                    in = ParameterIn.HEADER,
                    example = "Bearer eyJhbGciOiJIUzI1NiJ9..."
            )
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User data retrieved",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseUserActiveDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ListAPIError.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ListAPIError.class)
                    )
            )
    })
    ResponseEntity<ResponseUserActiveDTO> getUser(String token);
}
