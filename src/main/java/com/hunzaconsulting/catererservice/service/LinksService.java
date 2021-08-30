package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.controller.CatererController;
import com.hunzaconsulting.catererservice.payload.LinksResponse;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class LinksService {
    public LinksResponse getLinks() {
        LinksResponse response = new LinksResponse();
        response.add(linkTo(CatererController.class).slash("name").withRel("nameSearch"));
        response.add(linkTo(CatererController.class).slash("city").withRel("citySearch"));
        response.add(linkTo(CatererController.class).slash("save").withRel("saveCaterer"));
        return response;
    }
}
