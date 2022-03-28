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

    private int painLevel;

    @Enumerated(EnumType.STRING)
    private PainSite painSite;

    @Enumerated(EnumType.STRING)
    private PainCauses painCauses;

    @Enumerated(EnumType.STRING)
    private PainFrequency painFrequency;

    @Enumerated(EnumType.STRING)
    private PainActivities painActivities;

    private Boolean painDuration;

    @Enumerated(EnumType.STRING)
    private UrgencyRank urgencyRank;

    private LocalDateTime triageDateTime;

    @PrePersist
    protected void onCreate() {
        triageDateTime = LocalDateTime.now();
    }
}
