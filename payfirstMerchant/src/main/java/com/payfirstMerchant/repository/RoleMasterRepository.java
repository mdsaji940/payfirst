package com.payfirstMerchant.repository;

import com.payfirstMerchant.model.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer> {
}
