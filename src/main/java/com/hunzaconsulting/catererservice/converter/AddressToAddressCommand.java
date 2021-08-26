package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.AddressCommand;
import com.hunzaconsulting.catererservice.domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressCommand implements Converter<Address, AddressCommand>  {

    @Synchronized
    @Nullable
    @Override
    public AddressCommand convert(Address address) {
        if (null == address){
            return null;
        }
        final AddressCommand addressCommand = new AddressCommand();
        addressCommand.setCityName(address.getCityName());
        addressCommand.setStreetAddress(address.getStreetAddress());
        addressCommand.setPostalCode(address.getStreetAddress());
        return addressCommand;
    }
}
