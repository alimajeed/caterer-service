package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.AddressCommand;
import com.hunzaconsulting.catererservice.domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand, Address>  {

    @Synchronized
    @Nullable
    @Override
    public Address convert(AddressCommand addressCommand) {
        if (null == addressCommand){
            return null;
        }
        final Address address = new Address();
        address.setCityName(addressCommand.getCityName());
        address.setStreetAddress(addressCommand.getStreetAddress());
        address.setPostalCode(addressCommand.getStreetAddress());
        return address;
    }
}
