package com.minienergy.sellingapplication.controller;

import com.minienergy.sellingapplication.exception.EnergyAppException;
import com.minienergy.sellingapplication.responsevo.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {EnergyAppException.class})
    protected ResponseEntity<StatusResponse> handleException(EnergyAppException ex) {
        StatusResponse response = new StatusResponse();
        response.setResponseDescription(ex.getMessage());
        response.setResponseCode(ex.getErrorCode());
        return ResponseEntity.status(ex.getErrorCode()).body(response);
    }

}
