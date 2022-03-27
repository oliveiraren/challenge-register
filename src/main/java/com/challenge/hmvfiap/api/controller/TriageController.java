package com.challenge.hmvfiap.api.controller;

import com.challenge.hmvfiap.api.dto.TriageInputDTO;
import com.challenge.hmvfiap.api.dto.TriageOutputDTO;
import com.challenge.hmvfiap.domain.service.TriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/triage")
public class TriageController {

    private final TriageService triageService;

    @Autowired
    public TriageController(TriageService triageService) {
        this.triageService = triageService;
    }

    @PostMapping
    public TriageOutputDTO Evaluate(@Valid @RequestBody TriageInputDTO triageInputDTO) {
        return triageService.Evaluate(triageInputDTO);
    }
}
