package com.minienergy.sellingapplication.controller;

import com.minienergy.sellingapplication.requestvo.SubmitEnergyDetailsRequest;
import com.minienergy.sellingapplication.responsevo.DailyEnergyDetailsResponse;
import com.minienergy.sellingapplication.responsevo.SubmitEnergyDetailsResponse;
import com.minienergy.sellingapplication.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @PostMapping(value = "submit-energy-details-api")
    public ResponseEntity<SubmitEnergyDetailsResponse> submitEnergyDetailsByDate(@RequestBody SubmitEnergyDetailsRequest request) throws Exception {
        try {
            return ResponseEntity.ok(applicationService.submitEnergyDetailsByDate(request));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @GetMapping(value = "get-all-daily-energy-details-api")
    public ResponseEntity<DailyEnergyDetailsResponse> getAllDailyEnergyDetails() throws Exception {
        try {
            return ResponseEntity.ok(applicationService.getAllDailyEnergyDetails());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
