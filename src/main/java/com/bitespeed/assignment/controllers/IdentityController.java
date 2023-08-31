package com.bitespeed.assignment.controllers;

import com.bitespeed.assignment.dto.IdentityDto;
import com.bitespeed.assignment.dto.IdentityRequestDto;
import com.bitespeed.assignment.services.IdentityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/identity")
@RequiredArgsConstructor
@Validated
@Component
@Slf4j
public class IdentityController {

    @Autowired
    IdentityService identityService;


    @PostMapping(value = "/identify", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<IdentityDto> createIdentity(@RequestBody IdentityRequestDto identityRequestDto){
        log.info("Received REST request to create identity for IdentityRequestDto : {}", identityRequestDto);
        IdentityDto createIdentity = identityService.createIdentity(identityRequestDto);
        return new ResponseEntity<>(createIdentity, HttpStatus.OK);
    }

}
