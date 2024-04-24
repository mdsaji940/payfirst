package com.payfirstMerchant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ROLE_MASTER")
public class RoleMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private Boolean isActive;
    private Date createdDate;
    private Integer createdBy;
    private Date modifiedDate;
    private Integer modifiedBy;
    @ManyToMany(mappedBy = "roles")
    private Set<UserMaster> users = new HashSet<>();
}
