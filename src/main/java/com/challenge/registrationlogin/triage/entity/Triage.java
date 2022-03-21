package com.challenge.registrationlogin.triage.entity;

import com.challenge.registrationlogin.triage.enums.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
public class Triage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Boolean pain;

    private short painLevel;

    private PainSite painSite;

    private PainCauses painCauses;

    private PainFrequency painFrequency;

    private PainActivities painActivities;

    private PainDuration painDuration;

    private UrgencyRank urgencyRank;

    private LocalDateTime triageDateTime;

    @PrePersist
    protected void onCreate() {
        triageDateTime = LocalDateTime.now();
    }
}
