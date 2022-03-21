package com.challenge.registrationlogin.triage.helper;

import com.challenge.registrationlogin.triage.dto.TriageInputDTO;
import com.challenge.registrationlogin.triage.enums.UrgencyRank;
import org.springframework.stereotype.Service;

@Service
public class RankUrgencyHelper {

    public RankUrgencyHelper(){

    }

    public UrgencyRank Rank(TriageInputDTO triageInputDTO)
    {
        return UrgencyRank.POUCO_URGENTE;
    }
}
