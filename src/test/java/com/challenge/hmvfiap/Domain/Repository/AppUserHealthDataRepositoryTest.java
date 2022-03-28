package com.challenge.hmvfiap.Domain.Repository;

import com.challenge.hmvfiap.domain.entity.UserHealthData;
import com.challenge.hmvfiap.domain.repository.UserHealthDataRepository;
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
public class AppUserHealthDataRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserHealthDataRepository userHealthDataRepository;

    private UserHealthData userHealthData;

    @BeforeEach
    public void setup() {
        userHealthData = new UserHealthData();
    }

    @Test
    public void shouldAddUserHealthData() {
        userHealthDataRepository.save(userHealthData);
        assertNotNull(userHealthData.getUserId());
    }

    @Test
    public void ShouldFetchUserHealthData() {
        userHealthDataRepository.save(userHealthData);
        Optional<UserHealthData> userHealthDataDb = userHealthDataRepository.findById(userHealthData.getUserId());
        assertTrue(userHealthDataDb.isPresent());
    }

    @Test
    public void ShouldDeleteUserHealthData() {
        userHealthDataRepository.save(userHealthData);
        assertFalse(userHealthDataRepository.findAll().isEmpty());
        userHealthDataRepository.delete(userHealthData);
        assertTrue(userHealthDataRepository.findAll().isEmpty());
    }

    @Test
    public void ShouldUpdateUserHealthData() {
        userHealthDataRepository.save(userHealthData);
        userHealthData.setHeartAttack(true);
        userHealthDataRepository.save(userHealthData);
        Optional<UserHealthData> userHealthDataDb = userHealthDataRepository.findById(userHealthData.getUserId());
        assertTrue(userHealthDataDb.isPresent());
        assertEquals(userHealthData.getHeartAttack(), userHealthDataDb.get().getHeartAttack());
    }
}
