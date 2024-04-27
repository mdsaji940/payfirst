package com.payfirstMerchant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TMP_MERCHANT_INFO")
public class TmpMerchantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tmpMerchantId;
    private String ipAddress;
    private String encryptedTmpMerchantId;
    private String merchantIdentifier;
    private Integer affiliateId;

    // personal information
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String address;
    @OneToOne
    @JoinColumn(name = "countryId")
    private CountryMaster personalCountry;
    @OneToOne
    @JoinColumn(name = "stateId")
    private StateMaster personalState;
    private String personalCity;
    private String postalCode;
    private String personalMobile;
    private String personalPan;
    @OneToOne
    @JoinColumn(name = "personalDocId")
    private PersonalDocs personalDocs;
    private String personalIdNumber;
    private String businessName;
    @OneToOne
    @JoinColumn(name = "businessTypeId")
    private BusinessType businessType;

}
