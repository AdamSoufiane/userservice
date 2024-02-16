package application.ports.in;

import domain.entities.User;
import domain.exceptions.UserRegistrationException;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;

/**
 * Defines the input port for user registration within the application's core domain,
 * ensuring a clear separation between the application core and the adapter that receives user registration requests.
 * This interface serves as an entry point for the user registration process, following the hexagonal architecture principles.
 *
 * @PortType ApplicationCore
 * 
 * This port is part of the application layer and interacts with the domain layer and potentially external adapters for persistence and event publishing.
 * Implementations of this port should be annotated with @Service and recommended to use @Transactional for data consistency and integrity in the implementation class.
 * 
 * Integration with other parts of the system, such as event publishers or external services, should be considered and documented to guide implementers on correct integration practices.
 */
@Validated
public interface UserRegistrationPort {

    /**
     * Registers a new user in the system.
     * This method encapsulates the process of user registration, including validation and persistence.
     * 
     * @param user The user entity to be registered, which must meet basic validation criteria as enforced by annotations within the User class.
     * @throws UserRegistrationException if the user's information does not meet the required criteria or if specific failure scenarios occur, such as 'UserAlreadyExistsException' or 'ValidationException'.
     * The required criteria include but are not limited to valid name, email format, and password complexity.
     * 
     * @ApiOperation(value = "Register a new user", notes = "Registers a new user by validating the user entity and persisting it in the system. Detailed explanation on API versioning strategy, including URL and header versioning, is provided to ensure clear guidance for future API evolution.")
     * @ApiParam(name = "user", value = "User entity containing user details for registration", required = true)
     * @ApiResponses(value = {
     *     @ApiResponse(code = 200, message = "User registered successfully", examples = @Example(value = {@ExampleProperty(mediaType = "application/json", value = "{\"userId\": 1, \"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}")}),
     *     @ApiResponse(code = 400, message = "Validation error", examples = @Example(value = {@ExampleProperty(mediaType = "application/json", value = "{\"error\": \"Email format is invalid\"}")}),
     *     @ApiResponse(code = 409, message = "User already exists", examples = @Example(value = {@ExampleProperty(mediaType = "application/json", value = "{\"error\": \"User with this email already exists\"}")}),
     *     @ApiResponse(code = 500, message = "Internal server error")
     * })
     * 
     * @example Example usage:
     * UserRegistrationService.registerUser(new User("John Doe", "john.doe@example.com", "SecurePassword123"));
     * @example Validation failure scenario:
     * If the 'email' field does not match the email format, a ValidationException is thrown indicating the specific error.
     * 
     * @note It's recommended to use @Valid or @Validated on the 'user' parameter to enforce validation constraints defined in the User entity before the method logic is executed.
     * @note For different registration scenarios, leveraging validation groups can offer flexible validation schemes. This allows for partial updates versus full registration validation strategies.
     * 
     * @example Using validation groups:
     * @Validated({NewUser.class, ExistingUser.class}) User user
     * This allows for specifying different validation constraints for new user registration versus updating an existing user's information.
     * 
     * Integration with Swagger UI for API testing and exploration is recommended. Accessing the Swagger UI endpoint to interact with the API and using Swagger annotations to enrich the API documentation can enhance usability and developer experience.
     */
    void registerUser(@Valid User user) throws UserRegistrationException;
}