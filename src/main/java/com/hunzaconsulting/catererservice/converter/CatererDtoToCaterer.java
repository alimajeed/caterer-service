package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.domain.Caterer;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CatererDtoToCaterer implements Converter<CatererDto, Caterer>  {

    private ModelMapper modelMapper;

    @Synchronized
    @Nullable
    @Override
    public Caterer convert(CatererDto catererCommand) {
        if (null == catererCommand){
            return null;
        }
        return modelMapper.map(catererCommand, Caterer.class);
        /*final Caterer caterer = new Caterer();
        caterer.setName(catererCommand.getName());
        caterer.setAddress(addressConverter.convert(catererCommand.getAddress()));
        caterer.setCapacity(capacityConverter.convert(catererCommand.getCapacity()));
        caterer.setContact(contactConverter.convert(catererCommand.getContact()));*/
    }
}
