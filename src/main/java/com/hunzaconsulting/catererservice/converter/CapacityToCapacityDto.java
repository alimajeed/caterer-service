package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.dto.CapacityDto;
import com.hunzaconsulting.catererservice.domain.Capacity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CapacityToCapacityDto implements Converter<Capacity, CapacityDto>  {

    @Synchronized
    @Nullable
    @Override
    public CapacityDto convert(Capacity capacity) {
        if (null == capacity){
            return null;
        }
        final CapacityDto capacityCommand = new CapacityDto();
        capacityCommand.setMinGuestNo(capacity.getMinGuestNo());
        capacityCommand.setMaxGuestNo(capacity.getMaxGuestNo());
        return capacityCommand;
    }
}
