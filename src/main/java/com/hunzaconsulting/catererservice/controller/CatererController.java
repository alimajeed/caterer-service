package com.hunzaconsulting.catererservice.controller;

import com.hunzaconsulting.catererservice.config.PropertyConfig;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.service.CatererService;
import com.hunzaconsulting.catererservice.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/caterers")
@AllArgsConstructor
@Validated
@Slf4j
public class CatererController {

    private CatererService catererService;

    @PostMapping
    public ResponseEntity<CatererDto> saveCaterer(@Valid @RequestBody CatererDto catererDto) {
        log.info("[saveCaterer], caterer Info : {}", catererDto);
        CatererDto caterer = catererService.save(catererDto);
        ResponseEntity<CatererDto> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(caterer);
        log.info("[saveCaterer], response : {}", responseEntity);
        return responseEntity;
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<CatererDto> fetchCaterer (@PathVariable("name") String name) {
        log.info("[fetchCaterer], fetching caterer for name : {}", name);
        CatererDto caterer = catererService.getCaterer(name);
        ResponseEntity<CatererDto> responseEntity = ResponseEntity.ok().body(caterer);
        log.info("[fetchCaterer], response : {}", responseEntity);
        return responseEntity;
    }

    @GetMapping(path = "/{cityName}/all")
    public ResponseEntity<PagedResponse<CatererDto>> fetchAllCaterersByCity (
            @PathVariable("cityName") String cityName,
            @RequestParam(name = "page", required = false, defaultValue = PropertyConfig.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = PropertyConfig.DEFAULT_PAGE_SIZE) Integer size) {
        AppUtils.validatePageNumberAndSize(page, size);
        log.info("[fetchAllCaterersByCity], fetching page No : {} for caterers of city : {}", page, cityName);
        PagedResponse<CatererDto> caterersPage = catererService.getCityCaterers(cityName, page, size);
        ResponseEntity<PagedResponse<CatererDto>> responseEntity = ResponseEntity.ok().body(caterersPage);
        log.info("[fetchAllCaterersByCity], response : {}", responseEntity);
        return responseEntity;
    }





    @GetMapping
    public PagedResponse<CatererDto> fetchAllCaterers () {
        PagedResponse<CatererDto> catererList = catererService.getAllCaterers();
        return catererList;
    }
}
