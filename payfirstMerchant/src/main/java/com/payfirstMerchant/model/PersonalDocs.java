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
public class PersonalDocs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personalDocId;
    private String personalDocName;
    private Date createdDate;
    private Integer createdBy;
    private Date lastModifiedDate;
    private Integer lastModifiedBy;
    private Boolean isActive;
}
