package com.challenge.hmvfiap.domain.entity;

import com.challenge.hmvfiap.domain.enums.*;
import lombok.Data;

import javax.persistence.*;
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
