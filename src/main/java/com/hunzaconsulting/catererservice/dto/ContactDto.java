package com.hunzaconsulting.catererservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
