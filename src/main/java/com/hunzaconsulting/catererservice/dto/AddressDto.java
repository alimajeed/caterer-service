package com.hunzaconsulting.catererservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
