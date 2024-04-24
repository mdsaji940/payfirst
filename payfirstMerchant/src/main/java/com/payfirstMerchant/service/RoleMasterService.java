package com.payfirstMerchant.service;

import com.payfirstMerchant.model.RoleMaster;

public interface RoleMasterService {
    RoleMaster saveRoleMaster(RoleMaster roleMaster);

    RoleMaster getRoleById(int id);
}
