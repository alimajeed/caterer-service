package com.hunzaconsulting.catererservice.controller;

import com.hunzaconsulting.catererservice.config.PropertyConfig;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.exception.EntityNotFoundException;
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

@CrossOrigin
@RestController
@RequestMapping(CatererController.BASE_URL)
@AllArgsConstructor
@Validated
@Slf4j
public class CatererController {

    public static final String BASE_URL = "/api/v1/caterers";

    private CatererService catererService;

    @PostMapping(path = {"/save", "/save/"})
    public ResponseEntity<CatererDto> saveCaterer(@Valid @RequestBody CatererDto catererDto) {
        log.info("[saveCaterer], caterer Info : {}", catererDto);
        CatererDto caterer = catererService.save(catererDto);
        ResponseEntity<CatererDto> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(caterer);
        log.info("[saveCaterer], response : {}", responseEntity);
        return responseEntity;
    }

    @GetMapping(path = {"/name/{name}", "name/{name}/"})
    public ResponseEntity<PagedResponse<CatererDto>> fetchCaterersByName(
            @PathVariable("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = PropertyConfig.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = PropertyConfig.DEFAULT_PAGE_SIZE) Integer size) throws EntityNotFoundException{
        log.info("[fetchCaterersByName], fetching caterers for name : {}", name);
        PagedResponse<CatererDto> caterers = catererService.getPageByName(name, page, size);
        ResponseEntity<PagedResponse<CatererDto>> responseEntity = ResponseEntity.ok().body(caterers);
        log.info("[fetchCaterersByName], response : {}", responseEntity);
        return responseEntity;
    }

    @GetMapping(path = {"/id/{id}", "/id/{id}/"})
    public ResponseEntity<CatererDto> fetchCatererById (@PathVariable("id") String id) throws EntityNotFoundException{
        log.info("[fetchCatererById], fetching caterer for id : {}", id);
        CatererDto caterer = catererService.getById(id);
        ResponseEntity<CatererDto> responseEntity = ResponseEntity.ok().body(caterer);
        log.info("[fetchCatererById], response : {}", responseEntity);
        return responseEntity;
    }

    @GetMapping(path = {"/city/{cityName}", "/city/{cityName}/"})
    public ResponseEntity<PagedResponse<CatererDto>> fetchCaterersByCity(
            @PathVariable("cityName") String cityName,
            @RequestParam(name = "page", required = false, defaultValue = PropertyConfig.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = PropertyConfig.DEFAULT_PAGE_SIZE) Integer size) throws EntityNotFoundException{
        AppUtils.validatePageNumberAndSize(page, size);
        log.info("[fetchCaterersByCity], fetching page No : {} for caterers of city : {}", page - 1, cityName);
        PagedResponse<CatererDto> caterersPage = catererService.getPageByCity(cityName, page, size);
        ResponseEntity<PagedResponse<CatererDto>> responseEntity = ResponseEntity.ok().body(caterersPage);
        log.info("[fetchCaterersByCity], response : {}", responseEntity);
        return responseEntity;
    }
}
