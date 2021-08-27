package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.dto.AddressDto;
import com.hunzaconsulting.catererservice.domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddress implements Converter<AddressDto, Address>  {

    @Synchronized
    @Nullable
    @Override
    public Address convert(AddressDto addressCommand) {
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
