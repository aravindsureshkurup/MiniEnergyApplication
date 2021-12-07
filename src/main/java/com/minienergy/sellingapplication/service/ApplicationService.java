package com.minienergy.sellingapplication.service;

import com.minienergy.sellingapplication.model.DailyEnergyDetails;
import com.minienergy.sellingapplication.repository.DailyEnergyDetailsRepository;
import com.minienergy.sellingapplication.requestvo.SubmitEnergyDetailsRequest;
import com.minienergy.sellingapplication.responsevo.DailyEnergyDetailsResponse;
import com.minienergy.sellingapplication.responsevo.SubmitEnergyDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    DailyEnergyDetailsRepository dailyEnergyDetailsRepo;
    @Value("${discount.percentage}")
    private Double discountPercentage;
    @Value("#{'${discount.days}'.split(',')}")
    private List<Integer> discountDays;

    public SubmitEnergyDetailsResponse submitEnergyDetailsByDate(SubmitEnergyDetailsRequest request) throws Exception {
        SubmitEnergyDetailsResponse response = new SubmitEnergyDetailsResponse();
        request.getEnergyDetails().forEach(energyDetail ->
                energyDetail.setDiscountedPrice(getDiscountedPrice(energyDetail.getDate().toLocalDate(),
                        energyDetail.getPrice())));
        saveEnergyDetails(request.getEnergyDetails());
        response.setResponseCode("0000");
        response.setResponseDescription("Request Processed Successfully");
        return response;
    }

    private void saveEnergyDetails(List<DailyEnergyDetails> energyDetails) {
        dailyEnergyDetailsRepo.saveAll(energyDetails);
    }

    private Double getDiscountedPrice(LocalDate date, Double price) {

        return discountDays.contains(date.getDayOfWeek().getValue()) ?
                BigDecimal.valueOf((price * (100 - discountPercentage)) / 100 ).
                        setScale(2, RoundingMode.HALF_UP).doubleValue() : price;
    }


    public DailyEnergyDetailsResponse getAllDailyEnergyDetails() throws Exception {
        DailyEnergyDetailsResponse response = new DailyEnergyDetailsResponse();
        response.setDailyEnergyDetails(dailyEnergyDetailsRepo.findAll());
        return response;
    }
}
