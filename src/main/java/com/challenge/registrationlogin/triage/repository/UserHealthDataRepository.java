package com.challenge.registrationlogin.triage.repository;

import com.challenge.registrationlogin.triage.entity.UserHealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHealthDataRepository extends JpaRepository<UserHealthData, Long> {
}