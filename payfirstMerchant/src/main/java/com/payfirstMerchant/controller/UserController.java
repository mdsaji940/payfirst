package com.payfirstMerchant.controller;

import com.payfirstMerchant.exception.ResourceNotFoundException;
import com.payfirstMerchant.model.UserMaster;
import com.payfirstMerchant.model.dto.LoginRequest;
import com.payfirstMerchant.model.dto.ResponseFormat;
import com.payfirstMerchant.model.dto.UserSignupRequest;
import com.payfirstMerchant.repository.UserMasterRepository;
import com.payfirstMerchant.service.UserMasterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserMasterService userMasterService;
    private final UserMasterRepository userMasterRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseFormat> saveUser(@Valid @RequestBody UserSignupRequest userRequest, BindingResult bindingResult) {
        ResponseFormat responseFormat = new ResponseFormat();
        if (bindingResult.hasErrors()) {
            responseFormat.setMessage("Please set valid password");
            responseFormat.setStatusCode("404");
            responseFormat.setStatus("FAILED");
            return ResponseEntity.badRequest().body(responseFormat);
        }
        responseFormat = userMasterService.saveUser(userRequest);
        return new ResponseEntity<>(responseFormat, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseFormat> login(@RequestBody LoginRequest loginRequest) {
        UserMaster userMaster = userMasterRepository.findByUserName(loginRequest.getUserName()).orElseThrow(() -> new ResourceNotFoundException("User", "username", loginRequest.getUserName()));
        System.out.println("********** userMaster : " + userMaster);
        if (userMaster == null) {
            ResponseFormat responseFormat = new ResponseFormat();
            responseFormat.setMessage("Invalid userName");
            responseFormat.setStatus("FAILED");
            responseFormat.setStatusCode("401");
            return new ResponseEntity<>(responseFormat, HttpStatus.UNAUTHORIZED);
        }

        // check password expiry
        System.out.println("************ isExpired(userMaster) : " + isPassExpired(userMaster));
        if (!isPassExpired(userMaster)) {
            System.out.println("expired");
            ResponseFormat responseFormat = new ResponseFormat();
            responseFormat.setMessage("Password expired please reset password");
            responseFormat.setStatus("FAILED");
            responseFormat.setStatusCode("401");
            return new ResponseEntity<>(responseFormat, HttpStatus.UNAUTHORIZED);
        }

        // check lock realise time
        Date currDate = new Date();
        if (!currDate.after(userMaster.getLockRealiseTime())) {
            ResponseFormat responseFormat = new ResponseFormat();
            responseFormat.setMessage("Account locked please try after 15 minutes");
            responseFormat.setStatus("FAILED");
            responseFormat.setStatusCode("401");
            return new ResponseEntity<>(responseFormat, HttpStatus.UNAUTHORIZED);
        }

        // check attempts
        System.out.println("************ checkAttempts(userMaster) : " + checkAttempts(userMaster));
        if (!checkAttempts(userMaster)) {
            System.out.println("max attempt reached");

//            userMaster.setIsActive(false);
            userMaster.setLockRealiseTime(new Date(System.currentTimeMillis() + 15 * 60 * 1000)); // set 15 min
            userMaster.setAttempts(0);
            userMaster.setModifiedDate(new Date());
            userMaster.setModifiedBy(1);
            userMaster.setLastLoginDate(new Date());
            userMasterService.updateUser(userMaster);
            ResponseFormat responseFormat = new ResponseFormat();
            responseFormat.setMessage("Please login after 15 minutes");
            responseFormat.setStatus("FAILED");
            responseFormat.setStatusCode("401");
            return new ResponseEntity<>(responseFormat, HttpStatus.UNAUTHORIZED);
        }


        // match password
        boolean isAuthenticated = passwordEncoder.matches(loginRequest.getPassword(), (userMaster.getPassword()));
        System.out.println("********* isAuthenticated : " + isAuthenticated);
        if (!isAuthenticated) {
            System.out.println("************ password failed : ");
            // update attempts in userMaster
            userMaster.setAttempts(userMaster.getAttempts() + 1);
            userMaster.setModifiedDate(new Date());
            userMaster.setModifiedBy(1);
            userMaster.setLastLoginDate(new Date());
            userMasterService.updateUser(userMaster);

            System.out.println("************* updated usermaster by attempt : " + userMaster.getAttempts());

            System.out.println("invalid password");
            ResponseFormat responseFormat = new ResponseFormat();
            responseFormat.setMessage("Invalid password");
            responseFormat.setStatus("FAILED");
            responseFormat.setStatusCode("401");
            return new ResponseEntity<>(responseFormat, HttpStatus.UNAUTHORIZED);
        }

        // after success
        userMaster.setAttempts(0);
        userMaster.setModifiedDate(new Date());
        userMaster.setModifiedBy(1);
        userMaster.setLastLoginDate(new Date());
        userMasterService.updateUser(userMaster);
        ResponseFormat responseFormat = new ResponseFormat();
        responseFormat.setMessage("Login successfully");
        responseFormat.setStatus("success");
        responseFormat.setStatusCode("200");
        return new ResponseEntity<>(responseFormat, HttpStatus.OK);
    }

    public static boolean isPassExpired(UserMaster userMaster) {
        boolean status;
        Date date = new Date();
        Date expiryDate = userMaster.getExpiryDate();
        // check expiry date and attempts
        if (date.after(expiryDate)) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    public static boolean checkAttempts(UserMaster userMaster) {
        boolean status;
        Integer maxAttempts = 5;
        if (userMaster.getAttempts() >= maxAttempts) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }
}
