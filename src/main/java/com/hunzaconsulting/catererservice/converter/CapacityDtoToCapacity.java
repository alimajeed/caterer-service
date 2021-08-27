package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.dto.CapacityDto;
import com.hunzaconsulting.catererservice.domain.Capacity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CapacityDtoToCapacity implements Converter<CapacityDto, Capacity>  {

    @Synchronized
    @Nullable
    @Override
    public Capacity convert(CapacityDto capacityCommand) {
        if (null == capacityCommand){
            return null;
        }
        final Capacity capacity = new Capacity();
        capacity.setMinGuestNo(capacityCommand.getMinGuestNo());
        capacity.setMaxGuestNo(capacityCommand.getMaxGuestNo());
        return capacity;
    }
}
