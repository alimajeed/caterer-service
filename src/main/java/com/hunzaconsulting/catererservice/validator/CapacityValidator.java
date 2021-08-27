package com.hunzaconsulting.catererservice.validator;

import com.hunzaconsulting.catererservice.dto.CapacityDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapacityValidator implements ConstraintValidator<CapacityRange, CapacityDto> {

    @Override
    public boolean isValid(CapacityDto capacityDto, ConstraintValidatorContext constraintValidatorContext) {
        if (capacityDto.getMinGuestNo() > capacityDto.getMaxGuestNo()){
            return false;
        }
        return true;
    }
}
