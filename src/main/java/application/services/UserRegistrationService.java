package application.services;

import application.ports.out.EventPublisherPort;
import application.ports.out.UserRepositoryPort;
import domain.entities.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationService.class);

    private final UserRepositoryPort userRepositoryPort;
    private final EventPublisherPort eventPublisherPort;

    @Transactional
    public void registerUser(User user) {
        try {
            if (user == null || user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
                throw new IllegalArgumentException("User details must not be null");
            }
            userRepositoryPort.saveUser(user);
            eventPublisherPort.publishEvent("User registered: " + user.getEmail());
            logger.info("User successfully registered: " + user.getEmail());
        } catch (Exception e) {
            logger.error("Error registering user: " + user.getEmail(), e);
            throw e;
        }
    }
}