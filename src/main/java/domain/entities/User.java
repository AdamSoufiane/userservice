package domain.entities;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Represents the User entity with properties such as name, email, and password.
 * It encapsulates the basic data related to a user in the system.
 */
@Data
public class User {

    @NotBlank(message = "Name must be non-null and not empty")
    private String name;

    @NotBlank(message = "Email must be a valid email format")
    @Email(message = "Email must be a valid email format")
    private String email;

    @NotBlank(message = "Password must meet complexity requirements")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    // Note: Encryption logic has been moved to the service layer to adhere to Hexagonal Architecture principles.
    // This ensures separation of concerns and maintains architectural integrity.
}