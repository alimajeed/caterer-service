package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.CatererDto;
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
    }
}
