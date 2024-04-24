package com.payfirstMerchant.service.impl;

import com.payfirstMerchant.model.RoleMaster;
import com.payfirstMerchant.model.UserMaster;
import com.payfirstMerchant.model.dto.ResponseFormat;
import com.payfirstMerchant.model.dto.UserSignupRequest;
import com.payfirstMerchant.repository.UserMasterRepository;
import com.payfirstMerchant.service.RoleMasterService;
import com.payfirstMerchant.service.UserMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserMasterServiceImpl implements UserMasterService {
    private final UserMasterRepository userMasterRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleMasterService roleMasterService;
    @Override
    public ResponseFormat saveUser(UserSignupRequest userSignupRequest) {
        Calendar c= Calendar.getInstance();
        c.add(Calendar.DATE, 90);
        Date expiryDate=c.getTime();
        Optional<UserMaster> user = userMasterRepository.findByUserName(userSignupRequest.getUserName());
        if (user.isPresent()){
            return ResponseFormat.builder().status("FAILED").statusCode("404").message("User already exist with username").build();
        }
        UserMaster userMaster = new UserMaster();
        userMaster.setName(userSignupRequest.getName());
        userMaster.setEmail(userSignupRequest.getEmail());
        userMaster.setMobile(userSignupRequest.getMobile());
        userMaster.setUserName(userSignupRequest.getUserName());
        userMaster.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));
        userMaster.setLockRealiseTime(new Date());
        userMaster.setAttempts(0);
        userMaster.setCreatedDate(new Date());
        userMaster.setCreatedBy(1);
        userMaster.setModifiedDate(new Date());
        userMaster.setModifiedBy(0);
        userMaster.setIsActive(true);
        userMaster.setExpiryDate(expiryDate);

        // add user role of merchant
        RoleMaster roleMerchant = roleMasterService.getRoleById(1);
        Set<RoleMaster> roleList = new HashSet<>();
        roleList.add(roleMerchant);
        userMaster.setRoles(roleList);

        UserMaster newUser = userMasterRepository.save(userMaster);
        if (newUser==null){
            return ResponseFormat.builder().status("FAILED").statusCode("404").message("registration failed please tryAgain").build();
        }
        return ResponseFormat.builder().status("SUCCESS").statusCode("200").message("registration completed successfully").build();
    }

    @Override
    public UserMaster updateUser(UserMaster userMaster) {
        return userMasterRepository.save(userMaster);
    }
}
