package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.AddressCommand;
import com.hunzaconsulting.catererservice.command.CapacityCommand;
import com.hunzaconsulting.catererservice.domain.Address;
import com.hunzaconsulting.catererservice.domain.Capacity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CapacityCommandToCapacity implements Converter<CapacityCommand, Capacity>  {

    @Synchronized
    @Nullable
    @Override
    public Capacity convert(CapacityCommand capacityCommand) {
        if (null == capacityCommand){
            return null;
        }
        final Capacity capacity = new Capacity();
        capacity.setMinGuestNo(capacityCommand.getMinGuestNo());
        capacity.setMaxGuestNo(capacityCommand.getMaxGuestNo());
        return capacity;
    }
}
