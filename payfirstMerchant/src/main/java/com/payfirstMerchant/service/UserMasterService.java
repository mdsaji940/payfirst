package com.payfirstMerchant.service;

import com.payfirstMerchant.model.UserMaster;
import com.payfirstMerchant.model.dto.ResponseFormat;
import com.payfirstMerchant.model.dto.UserSignupRequest;

public interface UserMasterService {
    ResponseFormat saveUser(UserSignupRequest userSignupRequest);

    UserMaster updateUser(UserMaster userMaster);
}
