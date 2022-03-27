package com.challenge.hmvfiap.Domain.Repository;

import com.challenge.hmvfiap.domain.entity.User;
import com.challenge.hmvfiap.domain.enums.UserRole;
import com.challenge.hmvfiap.domain.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void shouldAddAnUser(){
        userRepository.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void ShouldFetchAnUser() {
        userRepository.save(user);
        Optional<User> userDb = userRepository.findById(user.getId());
        assertTrue(userDb.isPresent());
    }

    @Test
    public void ShouldDeleteAnUser() {
        userRepository.save(user);
        assertFalse(userRepository.findAll().isEmpty());
        userRepository.delete(user);
        assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void ShouldUpdateAnUser() {
        userRepository.save(user);
        user.setUserRole(UserRole.USER);
        userRepository.save(user);
        Optional<User> userDb = userRepository.findById(user.getId());
        assertTrue(userDb.isPresent());
        assertEquals(user.getUserRole(), userDb.get().getUserRole());
    }
}
