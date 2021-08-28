package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.payload.PagedResponse;

public interface CatererService {

    PagedResponse<CatererDto> getPageByCity(String cityName, Integer page, Integer size);

    PagedResponse<CatererDto> getPageByName(String name, Integer page, Integer size);

    CatererDto getById(String id);

    CatererDto save(CatererDto catererDto);
}
