package com.payfirstMerchant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY_MASTER")
public class CountryMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;
    private String countryIsoCode;
    private String countryName;
    private String currencyCode;
    private String countryPhoneCode;
    private String flagFilePath;
    private String placeholderText;
    private Boolean isBanned;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<StateMaster> states;

}
