package infrastructure.adapters.in.web.response;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

/**
 * Represents the error response object for failed user registrations.
 * Encapsulates details like status code and error messages.
 */
@Data
public class UserRegistrationErrorResponse {

    private HttpStatus statusCode;
    private String errorMessage;

    /**
     * Constructs a UserRegistrationErrorResponse with a status code and an error message.
     * @param statusCode the HTTP status code of the error
     * @param errorMessage the detailed message of the error
     */
    public UserRegistrationErrorResponse(HttpStatus statusCode, @NonNull String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}