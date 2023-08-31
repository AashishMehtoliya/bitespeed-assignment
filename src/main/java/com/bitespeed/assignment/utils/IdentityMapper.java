package com.bitespeed.assignment.utils;

import com.bitespeed.assignment.dto.IdentityRequestDto;
import com.bitespeed.assignment.model.Contact;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class IdentityMapper {

    public static Contact toContactFromIdentity(IdentityRequestDto identity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(identity, Contact.class);
    }
}
