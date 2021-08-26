package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.CatererCommand;
import com.hunzaconsulting.catererservice.domain.Caterer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CatererToCatererCommand implements Converter<Caterer, CatererCommand>  {

    private AddressToAddressCommand addressConverter;
    private CapacityToCapacityCommand capacityConverter;
    private ContactToContactCommand contactConverter;

    public CatererToCatererCommand(AddressToAddressCommand addressConverter, CapacityToCapacityCommand capacityConverter, ContactToContactCommand contactConverter) {
        this.addressConverter = addressConverter;
        this.capacityConverter = capacityConverter;
        this.contactConverter = contactConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public CatererCommand convert(Caterer caterer) {
        if (null == caterer){
            return null;
        }
        final CatererCommand catererCommand = new CatererCommand();
        catererCommand.setName(caterer.getName());
        catererCommand.setAddress(addressConverter.convert(caterer.getAddress()));
        catererCommand.setCapacity(capacityConverter.convert(caterer.getCapacity()));
        catererCommand.setContact(contactConverter.convert(caterer.getContact()));
        return catererCommand;
    }
}
