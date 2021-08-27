package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.dto.AddressDto;
import com.hunzaconsulting.catererservice.domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressDto implements Converter<Address, AddressDto>  {

    @Synchronized
    @Nullable
    @Override
    public AddressDto convert(Address address) {
        if (null == address){
            return null;
        }
        final AddressDto addressCommand = new AddressDto();
        addressCommand.setCityName(address.getCityName());
        addressCommand.setStreetAddress(address.getStreetAddress());
        addressCommand.setPostalCode(address.getStreetAddress());
        return addressCommand;
    }
}
