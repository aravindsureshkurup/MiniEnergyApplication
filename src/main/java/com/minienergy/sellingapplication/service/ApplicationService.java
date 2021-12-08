package com.minienergy.sellingapplication.service;

import com.minienergy.sellingapplication.exception.EnergyAppException;
import com.minienergy.sellingapplication.model.DailyEnergyDetails;
import com.minienergy.sellingapplication.repository.DailyEnergyDetailsRepository;
import com.minienergy.sellingapplication.requestvo.BulkSubmitEnergyDetailsRequest;
import com.minienergy.sellingapplication.responsevo.DailyEnergyDetailsResponse;
import com.minienergy.sellingapplication.responsevo.SubmitEnergyDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ApplicationService {

    @Autowired
    DailyEnergyDetailsRepository dailyEnergyDetailsRepo;
    @Value("${discount.percentage}")
    private Double discountPercentage;
    @Value("#{'${discount.days}'.split(',')}")
    private List<Integer> discountDays;

    public SubmitEnergyDetailsResponse submitEnergyDetailsByDate(DailyEnergyDetails energyDetail) throws EnergyAppException {
        SubmitEnergyDetailsResponse response = new SubmitEnergyDetailsResponse();
        try {
            if (energyDetail.getDate() == null || energyDetail.getEnergyType() == null)
                throw new EnergyAppException("Invalid Request", 400);
            energyDetail.setDiscountedPrice(getDiscountedPrice(energyDetail.getDate().toLocalDate(),
                    energyDetail.getPrice()));
            dailyEnergyDetailsRepo.save(energyDetail);
            response.setResponseCode(200);
            response.setResponseDescription("Request Processed Successfully");
        } catch (EnergyAppException ex) {
            log.error(ex.getMessage());
            throw new EnergyAppException(ex.getMessage(), ex, ex.getErrorCode());
        }
        return response;
    }


    private Double getDiscountedPrice(LocalDate date, Double price) throws EnergyAppException {
        if (price == null || price <= 0)
            throw new EnergyAppException("Invalid Price", 400);
        return discountDays.contains(date.getDayOfWeek().getValue()) ?
                BigDecimal.valueOf((price * (100 - discountPercentage)) / 100).
                        setScale(2, RoundingMode.HALF_UP).doubleValue() : price;
    }

    public DailyEnergyDetailsResponse getAllDailyEnergyDetails() throws EnergyAppException {
        DailyEnergyDetailsResponse response = new DailyEnergyDetailsResponse();
        response.setDailyEnergyDetails(dailyEnergyDetailsRepo.findAll());
        if (response.getDailyEnergyDetails().isEmpty())
            throw new EnergyAppException("No records found", 200);
        return response;
    }

    public SubmitEnergyDetailsResponse submitEnergyDetailsByDateBulk(BulkSubmitEnergyDetailsRequest request) throws EnergyAppException {
        SubmitEnergyDetailsResponse response = new SubmitEnergyDetailsResponse();
        if (request.getEnergyDetails().isEmpty())
            throw new EnergyAppException("Request is Empty", 400);
        request.getEnergyDetails().forEach(energyDetail ->
        {
            try {
                energyDetail.setDiscountedPrice(getDiscountedPrice(energyDetail.getDate().toLocalDate(),
                        energyDetail.getPrice()));
            } catch (EnergyAppException ex) {
                log.error(ex.getMessage());
            }
        });
        saveEnergyDetails(request.getEnergyDetails());
        response.setResponseCode(200);
        response.setResponseDescription("Request Processed Successfully");
        return response;
    }

    private void saveEnergyDetails(List<DailyEnergyDetails> energyDetails) {
        dailyEnergyDetailsRepo.saveAll(energyDetails);
    }
}
