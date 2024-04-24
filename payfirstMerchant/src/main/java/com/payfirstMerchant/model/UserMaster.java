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
@Table(name = "USER_MASTER")
public class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    private String email;
    private String mobile;
    private String userName;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleMaster> roles = new HashSet<>();
    private Integer attempts;
    private String token;
    private Boolean isActive;
    private Date createdDate;
    private Integer createdBy;
    private Date modifiedDate;
    private Integer modifiedBy;
    private Date ExpiryDate;
    private Date lockRealiseTime;
    private Date lastLoginDate;
}
