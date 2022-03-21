package com.challenge.registrationlogin.triage.service;

import com.challenge.registrationlogin.triage.dto.TriageInputDTO;
import com.challenge.registrationlogin.triage.dto.TriageOutputDTO;
import com.challenge.registrationlogin.triage.entity.Triage;
import com.challenge.registrationlogin.triage.entity.UserHealthData;
import com.challenge.registrationlogin.triage.enums.UrgencyRank;
import com.challenge.registrationlogin.triage.helper.RankUrgencyHelper;
import com.challenge.registrationlogin.triage.repository.TriageRepository;
import com.challenge.registrationlogin.triage.repository.UserHealthDataRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TriageService {

    private final TriageRepository triageRepository;
    private final UserHealthDataRepository userHealthDataRepository;
    private final ModelMapper modelMapper;
    private final RankUrgencyHelper rankUrgencyHelper;

    @Autowired
    public TriageService(TriageRepository triageRepository, UserHealthDataRepository userHealthDataRepository, ModelMapper modelMapper, RankUrgencyHelper rankUrgencyHelper) {
        this.triageRepository = triageRepository;
        this.userHealthDataRepository = userHealthDataRepository;
        this.rankUrgencyHelper = rankUrgencyHelper;
        this.modelMapper = modelMapper;
    }

    public TriageOutputDTO Evaluate(TriageInputDTO triageInputDTO) {
        UrgencyRank urgencyRank = rankUrgencyHelper.Rank(triageInputDTO);

        UserHealthData userHealthData = toUserHealthDataEntity(triageInputDTO);
        UserHealthData dbUserHealthData = userHealthDataRepository.findById(triageInputDTO.getUserId()).orElse(null);

        if(!userHealthData.equals(dbUserHealthData))
            userHealthDataRepository.save(userHealthData);

        Triage triage = toTriageEntity(triageInputDTO);
        triage.setUrgencyRank(urgencyRank);
        triageRepository.save(triage);

        TriageOutputDTO triageOutputDTO = new TriageOutputDTO();
        triageOutputDTO.setUrgencyRank(urgencyRank);

        return triageOutputDTO;
    }

    private Triage toTriageEntity(TriageInputDTO triageInputDTO) {
        return modelMapper.map(triageInputDTO, Triage.class);
    }

    private UserHealthData toUserHealthDataEntity(TriageInputDTO triageInputDTO) {
        return modelMapper.map(triageInputDTO, UserHealthData.class);
    }

}