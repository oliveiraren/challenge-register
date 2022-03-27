package com.challenge.hmvfiap.helper;

import com.challenge.hmvfiap.api.dto.TriageInputDTO;
import com.challenge.hmvfiap.domain.enums.UrgencyRank;
import org.springframework.stereotype.Service;

@Service
public class RankUrgencyHelper {

    public RankUrgencyHelper() { }

    public UrgencyRank Rank(TriageInputDTO triageInputDTO) {
        int painLevel = triageInputDTO.getPainLevel();
        UrgencyRank urgencyRank;

        if(isBetween(painLevel, 9,10))
            urgencyRank = UrgencyRank.EMERGENCIA;
        else if(isBetween(painLevel, 7,8))
            urgencyRank = UrgencyRank.MUITO_URGENTE;
        else if(isBetween(painLevel, 5,6))
            urgencyRank = UrgencyRank.URGENTE;
        else
            urgencyRank = UrgencyRank.POUCO_URGENTE;

        return urgencyRank;
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
