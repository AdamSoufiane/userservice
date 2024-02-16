package infrastructure.adapters.in.web;

import domain.exceptions.UserRegistrationException;
import infrastructure.adapters.in.web.request.UserRequest;
import infrastructure.adapters.in.web.response.UserRegistrationErrorResponse;
import infrastructure.adapters.in.web.response.UserResponse;
import application.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userRegistrationService.registerUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (UserRegistrationException e) {
            UserRegistrationErrorResponse errorResponse = new UserRegistrationErrorResponse(e.getStatusCode(), e.getMessage());
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(errorResponse);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new UserRegistrationErrorResponse(500, "An unexpected error occurred."));
        }
    }
}