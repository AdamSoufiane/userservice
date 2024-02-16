package infrastructure.adapters.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import domain.enums.ResponseStatus;

@Data
public class UserResponse {

    @JsonProperty("status")
    private ResponseStatus status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    public UserResponse() {}

    public UserResponse(ResponseStatus status, String message, Long userId, String name, String email) {
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}