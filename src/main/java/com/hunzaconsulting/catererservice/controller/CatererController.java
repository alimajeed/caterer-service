package com.hunzaconsulting.catererservice.controller;

import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.service.CatererServiceImpl;
import com.hunzaconsulting.catererservice.utils.AppConstants;
import com.hunzaconsulting.catererservice.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/caterers")
@AllArgsConstructor
@Validated
@Slf4j
public class CatererController {

    private CatererServiceImpl catererService;

    @PostMapping
    public ResponseEntity<CatererDto> saveCaterer(@Valid @RequestBody CatererDto catererDto, BindingResult result) {
        log.info("caterer Info : {}", catererDto);
        CatererDto caterer = catererService.save(catererDto);
        ResponseEntity<CatererDto> responseEntity = new ResponseEntity<>(caterer, HttpStatus.CREATED);
        log.info("response : {}", responseEntity);
        return responseEntity;
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<CatererDto> fetchCaterer (@PathVariable("name") String name) {
        log.info("fetching caterer for name : {}", name);
        CatererDto caterer = catererService.getCaterer(name);
        ResponseEntity<CatererDto> responseEntity = new ResponseEntity<>(caterer, HttpStatus.OK);
        log.info("response : {}", responseEntity);
        return responseEntity;
    }

    @GetMapping(path = "/{cityName}/all")
    public ResponseEntity<PagedResponse<CatererDto>> fetchAllCaterersByCity (
            @PathVariable("cityName") String cityName,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        AppUtils.validatePageNumberAndSize(page, size);
        log.info("fetching page No : {} for caterers of city : {}", page, cityName);
        PagedResponse<CatererDto> caterersPage = catererService.getCityCaterers(cityName, page, size);
        ResponseEntity<PagedResponse<CatererDto>> responseEntity = new ResponseEntity<>(caterersPage, HttpStatus.OK);
        log.info("response : {}", responseEntity);
        return responseEntity;
    }





    @GetMapping
    public PagedResponse<CatererDto> fetchAllCaterers () {
        PagedResponse<CatererDto> catererList = catererService.getAllCaterers();
        return catererList;
    }
}
