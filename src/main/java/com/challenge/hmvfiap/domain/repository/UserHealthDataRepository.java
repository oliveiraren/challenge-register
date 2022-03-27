package com.challenge.hmvfiap.domain.repository;

import com.challenge.hmvfiap.domain.entity.UserHealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHealthDataRepository extends JpaRepository<UserHealthData, Long> {
}