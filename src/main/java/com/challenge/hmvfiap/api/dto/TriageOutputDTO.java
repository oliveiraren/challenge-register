package com.challenge.hmvfiap.api.dto;

import com.challenge.hmvfiap.domain.enums.UrgencyRank;
import lombok.Data;

@Data
public class TriageOutputDTO {

    private UrgencyRank urgencyRank;
}
