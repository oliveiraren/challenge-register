package com.challenge.registrationlogin.triage.repository;

import com.challenge.registrationlogin.triage.entity.Triage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriageRepository extends JpaRepository<Triage, Long> {
}