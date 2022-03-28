package com.challenge.hmvfiap.domain.repository;

import com.challenge.hmvfiap.domain.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByUserName(String userName);

    @Query("UPDATE AppUser a SET a.enabled = TRUE WHERE a.email = ?1")
    void enableAppUser(String email);
}
