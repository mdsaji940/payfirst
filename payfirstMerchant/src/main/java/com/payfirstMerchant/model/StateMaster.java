package com.payfirstMerchant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STATE_MASTER")
public class StateMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;
    @ManyToOne
    @JoinColumn(name = "country_Id")
    private CountryMaster country;
    private String stateName;
    private Boolean isActive;
    private Date createdDate;
}
