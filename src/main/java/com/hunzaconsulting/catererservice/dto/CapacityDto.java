package com.hunzaconsulting.catererservice.dto;

import com.hunzaconsulting.catererservice.validator.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Range
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CapacityDto {

    @Positive
    @NotNull
    private Integer minGuestNo;

    @Positive
    @NotNull
    private Integer maxGuestNo;
}
