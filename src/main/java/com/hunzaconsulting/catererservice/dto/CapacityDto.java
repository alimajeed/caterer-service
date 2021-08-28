package com.hunzaconsulting.catererservice.dto;

import com.hunzaconsulting.catererservice.validator.Range;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Range
public class CapacityDto {

    @Positive
    @NotNull
    private Integer minGuestNo;

    @Positive
    @NotNull
    private Integer maxGuestNo;
}
