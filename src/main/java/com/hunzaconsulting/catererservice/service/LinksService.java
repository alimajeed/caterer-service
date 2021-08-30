package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.controller.CatererController;
import com.hunzaconsulting.catererservice.payload.LinksResponse;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

@Service
public class LinksService {
    public LinksResponse getLinks() {
        LinksResponse response = new LinksResponse();
        response.add(Link.of(CatererController.BASE_URL + "/name", "nameSearch"));
        response.add(Link.of(CatererController.BASE_URL + "/city", "citySearch"));
        response.add(Link.of(CatererController.BASE_URL + "/save", "saveCaterer"));
        return response;
    }
}
