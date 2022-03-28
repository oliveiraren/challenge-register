package com.challenge.hmvfiap.Domain.Repository;

import com.challenge.hmvfiap.domain.entity.ConfirmationToken;
import com.challenge.hmvfiap.domain.repository.ConfirmationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ConfirmationTokenRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    private ConfirmationToken confirmationToken;

    @BeforeEach
    public void setup() {
        confirmationToken = new ConfirmationToken();
    }

    @Test
    public void shouldAddTriageRecord() {
        confirmationTokenRepository.save(confirmationToken);
        assertNotNull(confirmationToken.getToken());
    }

}
