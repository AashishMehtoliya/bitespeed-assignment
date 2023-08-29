package com.bitespeed.assignment.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class IdentityRequestDto {

    private String email;
    private Integer phoneNumber;
}
