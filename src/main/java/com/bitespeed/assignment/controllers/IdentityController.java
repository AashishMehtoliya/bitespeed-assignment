package com.bitespeed.assignment.controllers;

import com.bitespeed.assignment.dto.IdentityDto;
import com.bitespeed.assignment.dto.IdentityRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/identify", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IdentityDto>> createIdentity(@RequestBody IdentityRequestDto identityRequestDto){
        log.info("Received REST request to create identity for IdentityRequestDto : {}", identityRequestDto);
        return null;
    }

}
