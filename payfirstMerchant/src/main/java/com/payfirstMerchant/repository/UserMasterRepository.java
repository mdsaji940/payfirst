package com.payfirstMerchant.repository;

import com.payfirstMerchant.model.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

    Optional<UserMaster> findByUserName(String userName);
}
