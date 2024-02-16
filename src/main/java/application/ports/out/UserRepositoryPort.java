package application.ports.out;

import domain.entities.User;
import domain.exceptions.UserPersistenceException;

public interface UserRepositoryPort {
    void saveUser(User user) throws UserPersistenceException;
}