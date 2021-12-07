package com.minienergy.sellingapplication.requestvo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
public class EnergyDetails {
    private String energyType;
    private Double price;
    private Double discountedPrice;
    private String units;
    private Date date;
}
