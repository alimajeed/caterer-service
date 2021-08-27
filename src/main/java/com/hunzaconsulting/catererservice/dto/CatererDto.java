package com.hunzaconsulting.catererservice.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class CatererDto extends RepresentationModel<CatererDto> {

    @Null
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @Valid
    private AddressDto address;

    @Valid
    private CapacityDto capacity;

    @Valid
    private ContactDto contact;
}
