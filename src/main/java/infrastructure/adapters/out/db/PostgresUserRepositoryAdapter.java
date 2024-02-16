package infrastructure.adapters.out.db;

import application.ports.out.UserRepositoryPort;
import domain.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PostgresUserRepositoryAdapter implements UserRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(PostgresUserRepositoryAdapter.class);

    @PersistenceContext
    @Getter @Setter
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveUser(User user) {
        try {
            if (user.getId() == null) {
                entityManager.persist(user);
                logger.info("User persisted: {}", user);
            } else {
                entityManager.merge(user);
                logger.info("User merged: {}", user);
            }
        } catch (PersistenceException | DataAccessException e) {
            logger.error("Error saving user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        try {
            User user = entityManager.find(User.class, id);
            logger.debug("User found: {}", user);
            return user;
        } catch (PersistenceException e) {
            logger.error("Error finding user by ID: {}", e.getMessage(), e);
            throw e;
        }
    }
}