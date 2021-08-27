package com.hunzaconsulting.catererservice.dto;

import com.hunzaconsulting.catererservice.validator.CapacityRange;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@CapacityRange
public class CapacityDto {

    @Positive
    @NotNull
    private Integer minGuestNo;

    @Positive
    @NotNull
    private Integer maxGuestNo;
}
