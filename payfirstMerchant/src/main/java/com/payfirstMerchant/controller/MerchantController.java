package com.payfirstMerchant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @PostMapping("/sign-up")
    public ResponseEntity<String> merchantSignup(){

        return null;
    }

}
