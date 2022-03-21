package com.challenge.registrationlogin.triage.dto;

import com.challenge.registrationlogin.triage.enums.UrgencyRank;
import lombok.Data;

@Data
public class TriageOutputDTO {

    private UrgencyRank urgencyRank;
}
