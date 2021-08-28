package com.hunzaconsulting.catererservice.mapper;

import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import org.mapstruct.Mapper;

@Mapper
public interface CatererMapper {
    Caterer catererDtoToCaterer(CatererDto catererDto);
    CatererDto catererToCatererDto(Caterer caterer);
}
