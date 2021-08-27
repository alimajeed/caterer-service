package com.hunzaconsulting.catererservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddressDto {

    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s][a-zA-Z]+)*$")
    @NotNull
    @NotBlank
    private String cityName;

    @NotNull
    @NotBlank
    private String streetAddress;

    private String postalCode;

}
