package com.challenge.hmvfiap.Domain.Repository;

import com.challenge.hmvfiap.domain.entity.AppUser;
import com.challenge.hmvfiap.domain.enums.UserRole;
import com.challenge.hmvfiap.domain.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppAppUserRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AppUserRepository appUserRepository;

    private AppUser appUser;

    @BeforeEach
    public void setup() {
        appUser = new AppUser();
    }

    @Test
    public void shouldAddAnUser() {
        appUserRepository.save(appUser);
        assertNotNull(appUser.getId());
    }

    @Test
    public void ShouldFetchAnUser() {
        appUserRepository.save(appUser);
        Optional<AppUser> userDb = appUserRepository.findById(appUser.getId());
        assertTrue(userDb.isPresent());
    }

    @Test
    public void ShouldDeleteAnUser() {
        appUserRepository.save(appUser);
        assertFalse(appUserRepository.findAll().isEmpty());
        appUserRepository.delete(appUser);
        assertTrue(appUserRepository.findAll().isEmpty());
    }

    @Test
    public void ShouldUpdateAnUser() {
        appUserRepository.save(appUser);
        appUser.setUserRole(UserRole.USER);
        appUserRepository.save(appUser);
        Optional<AppUser> userDb = appUserRepository.findById(appUser.getId());
        assertTrue(userDb.isPresent());
        assertEquals(appUser.getUserRole(), userDb.get().getUserRole());
    }
}
