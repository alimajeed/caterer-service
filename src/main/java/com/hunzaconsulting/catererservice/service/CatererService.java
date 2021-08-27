package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.payload.PagedResponse;

public interface CatererService {

    PagedResponse<CatererDto> getAllCaterers ();

    PagedResponse<CatererDto> getCityCaterers(String cityName, Integer page, Integer size);

    CatererDto getCaterer(String name);

    CatererDto save(CatererDto catererDto);
}
