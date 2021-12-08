package com.minienergy.sellingapplication.controller;

import com.minienergy.sellingapplication.exception.EnergyAppException;
import com.minienergy.sellingapplication.model.DailyEnergyDetails;
import com.minienergy.sellingapplication.requestvo.BulkSubmitEnergyDetailsRequest;
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
    public ResponseEntity<SubmitEnergyDetailsResponse> submitEnergyDetailsByDate(@RequestBody DailyEnergyDetails request) throws EnergyAppException {
        try {
            return ResponseEntity.ok(applicationService.submitEnergyDetailsByDate(request));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @PostMapping(value = "bulk-submit-energy-details-api")
    public ResponseEntity<SubmitEnergyDetailsResponse> submitEnergyDetailsByDateBulk(@RequestBody BulkSubmitEnergyDetailsRequest request) throws EnergyAppException {
        try {
            return ResponseEntity.ok(applicationService.submitEnergyDetailsByDateBulk(request));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @GetMapping(value = "get-all-daily-energy-details-api")
    public ResponseEntity<DailyEnergyDetailsResponse> getAllDailyEnergyDetails() throws EnergyAppException {
        try {
            return ResponseEntity.ok(applicationService.getAllDailyEnergyDetails());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
