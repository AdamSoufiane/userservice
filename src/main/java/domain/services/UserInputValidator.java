package domain.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInputValidator {

    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");

    public boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean validateEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public boolean validatePassword(String password) {
        return passwordPattern.matcher(password).matches();
    }
}