package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.payload.PagedResponse;

public interface CatererService {

    PagedResponse<CatererDto> getCityCaterers(String cityName, Integer page, Integer size);

    PagedResponse<CatererDto> getCaterersByName(String name, Integer page, Integer size);

    CatererDto getCatererById(String id);

    CatererDto save(CatererDto catererDto);
}
