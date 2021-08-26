package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.AddressCommand;
import com.hunzaconsulting.catererservice.command.CatererCommand;
import com.hunzaconsulting.catererservice.domain.Address;
import com.hunzaconsulting.catererservice.domain.Caterer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CatererCommandToCaterer implements Converter<CatererCommand, Caterer>  {

    private AddressCommandToAddress addressConverter;
    private CapacityCommandToCapacity capacityConverter;
    private ContactCommandToContact contactConverter;

    public CatererCommandToCaterer(AddressCommandToAddress addressConverter, CapacityCommandToCapacity capacityConverter, ContactCommandToContact contactConverter) {
        this.addressConverter = addressConverter;
        this.capacityConverter = capacityConverter;
        this.contactConverter = contactConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Caterer convert(CatererCommand catererCommand) {
        if (null == catererCommand){
            return null;
        }
        final Caterer caterer = new Caterer();
        caterer.setName(catererCommand.getName());
        caterer.setAddress(addressConverter.convert(catererCommand.getAddress()));
        caterer.setCapacity(capacityConverter.convert(catererCommand.getCapacity()));
        caterer.setContact(contactConverter.convert(catererCommand.getContact()));
        return caterer;
    }
}
