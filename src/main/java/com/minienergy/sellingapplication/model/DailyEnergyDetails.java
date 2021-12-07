package com.minienergy.sellingapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@IdClass(EnergyId.class)
public class DailyEnergyDetails {
    @Id
    private String energyType;
    private Double price;
    private Double discountedPrice;
    private String units;
    @Id
    private Date date;
}
