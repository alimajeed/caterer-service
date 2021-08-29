package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.controller.CatererController;
import com.hunzaconsulting.catererservice.payload.LinksResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class LinksService {
    public LinksResponse getLinks() {
        LinksResponse response = new LinksResponse();
        response.add(linkTo(CatererController.class).slash("name").slash("${name}?page=${page}").withRel("nameSearch"));
        response.add(linkTo(CatererController.class).slash("city").slash("${city}?page=${page}").withRel("citySearch"));
        response.add(linkTo(CatererController.class).slash("save").withRel("saveCaterer"));
        return response;
    }
}
