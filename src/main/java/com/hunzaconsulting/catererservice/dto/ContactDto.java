package com.hunzaconsulting.catererservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContactDto {

    private String phoneNumber;

    @NotNull
    @NotBlank
    private String mobileNumber;

    @Email
    @NotNull
    @NotBlank
    private String email;
}
