package com.hunzaconsulting.catererservice.mapper;

import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CatererMapperImpl implements CatererMapper{

    private ModelMapper modelMapper;

    @Override
    public Caterer catererDtoToCaterer(CatererDto catererDto) {
        if (null == catererDto){
            return null;
        }
        return modelMapper.map(catererDto, Caterer.class);
    }

    @Override
    public CatererDto catererToCatererDto(Caterer caterer) {
        if (null == caterer){
            return null;
        }
        return modelMapper.map(caterer, CatererDto.class);
    }
}
