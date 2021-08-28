package com.hunzaconsulting.catererservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
