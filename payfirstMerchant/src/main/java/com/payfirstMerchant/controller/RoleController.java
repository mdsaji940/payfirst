package com.payfirstMerchant.controller;

import com.payfirstMerchant.model.RoleMaster;
import com.payfirstMerchant.model.dto.ResponseFormat;
import com.payfirstMerchant.service.RoleMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private final RoleMasterService roleMasterService;

    @PostMapping("/addRole")
    public ResponseFormat addRole(@RequestBody RoleMaster roleMaster){
        try{
            RoleMaster result = roleMasterService.saveRoleMaster(roleMaster);
            if (result != null){
                return new ResponseFormat("Success", "Role created successfully", "200");
            }
            return new ResponseFormat("Failed", "Failed to add new Role", "403");
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            return new ResponseFormat("Failed", "Getting Exception :" + ex.getLocalizedMessage(), "403");
        }
    }

}
