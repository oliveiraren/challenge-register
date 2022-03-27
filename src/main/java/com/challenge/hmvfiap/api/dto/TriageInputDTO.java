package com.challenge.hmvfiap.api.dto;

import com.challenge.hmvfiap.domain.enums.*;
import lombok.Data;

@Data
public class TriageInputDTO {

    private Long userId;

    private Boolean heartAttack;

    private String heartAttackKinship;

    private Boolean obese;

    private Boolean medcation;

    private String medcationWhich;

    private Boolean allergy;

    private String drugAllergy;

    private Boolean pain;

    private short painLevel;

    private PainSite painSite;

    private PainCauses painCauses;

    private PainFrequency painFrequency;

    private PainActivities painActivities;

    private PainDuration painDuration;
}
