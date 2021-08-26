package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.CapacityCommand;
import com.hunzaconsulting.catererservice.domain.Capacity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CapacityToCapacityCommand implements Converter<Capacity, CapacityCommand>  {

    @Synchronized
    @Nullable
    @Override
    public CapacityCommand convert(Capacity capacity) {
        if (null == capacity){
            return null;
        }
        final CapacityCommand capacityCommand = new CapacityCommand();
        capacityCommand.setMinGuestNo(capacity.getMinGuestNo());
        capacityCommand.setMaxGuestNo(capacity.getMaxGuestNo());
        return capacityCommand;
    }
}
