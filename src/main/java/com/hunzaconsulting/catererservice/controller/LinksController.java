package com.hunzaconsulting.catererservice.controller;

import com.hunzaconsulting.catererservice.payload.LinksResponse;
import com.hunzaconsulting.catererservice.service.LinksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({"", "/"})
@AllArgsConstructor
@Validated
@Slf4j
public class LinksController {
    private LinksService linksService;

    @GetMapping
    public ResponseEntity<LinksResponse> getLinks(){
        return ResponseEntity.ok().body(linksService.getLinks());
    }



}
