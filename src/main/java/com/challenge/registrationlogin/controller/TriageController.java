package com.challenge.registrationlogin.controller;

import com.challenge.registrationlogin.triage.dto.TriageInputDTO;
import com.challenge.registrationlogin.triage.dto.TriageOutputDTO;
import com.challenge.registrationlogin.triage.service.TriageService;

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
