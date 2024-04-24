package com.payfirstMerchant.service.impl;


import com.payfirstMerchant.model.RoleMaster;
import com.payfirstMerchant.repository.RoleMasterRepository;
import com.payfirstMerchant.service.RoleMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RoleMasterServiceImpl implements RoleMasterService {

    private final RoleMasterRepository roleMasterRepository;
    @Override
    public RoleMaster saveRoleMaster(RoleMaster roleMaster) {
        roleMaster.setCreatedBy(1);
        roleMaster.setCreatedDate(new Date());
        roleMaster.setIsActive(true);
        roleMaster.setModifiedBy(0);
        roleMaster.setModifiedDate(null);
        RoleMaster result = this.roleMasterRepository.save(roleMaster);
        return result;
    }

    @Override
    public RoleMaster getRoleById(int id) {
        return roleMasterRepository.findById(1).get();
    }
}
