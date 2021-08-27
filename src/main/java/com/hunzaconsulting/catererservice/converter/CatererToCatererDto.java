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
public class CatererToCatererDto implements Converter<Caterer, CatererDto>  {

    private ModelMapper modelMapper;

    @Synchronized
    @Nullable
    @Override
    public CatererDto convert(Caterer caterer) {
        if (null == caterer){
            return null;
        }
        return modelMapper.map(caterer, CatererDto.class);
        /*final CatererCommand catererCommand = new CatererCommand();
        catererCommand.setId(caterer.getId());
        catererCommand.setName(caterer.getName());
        catererCommand.setAddress(addressConverter.convert(caterer.getAddress()));
        catererCommand.setCapacity(capacityConverter.convert(caterer.getCapacity()));
        catererCommand.setContact(contactConverter.convert(caterer.getContact()));*/
    }
}
