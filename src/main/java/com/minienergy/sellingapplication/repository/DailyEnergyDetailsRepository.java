package com.minienergy.sellingapplication.repository;

import com.minienergy.sellingapplication.model.DailyEnergyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyEnergyDetailsRepository extends JpaRepository<DailyEnergyDetails,Long> {
}
