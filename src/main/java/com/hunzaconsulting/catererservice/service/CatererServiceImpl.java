package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.controller.CatererController;
import com.hunzaconsulting.catererservice.converter.CatererDtoToCaterer;
import com.hunzaconsulting.catererservice.converter.CatererToCatererDto;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.exception.EntityNotFoundException;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import com.hunzaconsulting.catererservice.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@Service
@AllArgsConstructor
@CacheConfig
public class CatererServiceImpl implements CatererService{

    private CatererRepository catererRepository;
    private CatererToCatererDto catererToCatererDto;
    private CatererDtoToCaterer catererDtoToCaterer;

    private ModelMapper modelMapper;
    private CacheManager cacheManager;

    //TODO remove later. just for experiments
    @Override
    public PagedResponse<CatererDto> getAllCaterers (){
        Pageable pageable = PageRequest.of(0,2);
        Page<Caterer> caterers = catererRepository.findAll(pageable);
        if (caterers.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), caterers.getNumber(), caterers.getSize(), caterers.getTotalElements(),
                    caterers.getTotalPages(), caterers.isLast());
        }

        List<CatererDto> catererDtoList = Arrays.asList(modelMapper.map(caterers.getContent(), CatererDto[].class));
        return new PagedResponse<>(catererDtoList, caterers.getNumber(), caterers.getSize(), caterers.getTotalElements(), caterers.getTotalPages(),
                caterers.isLast());
    }

    @Override
    @Cacheable(value = "caterersByCity")
    public PagedResponse<CatererDto> getCityCaterers(String cityName, Integer page, Integer size){
        log.debug("fetching caterers for city : {}", cityName);
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);

        Page<Caterer> caterers = catererRepository.findCaterersByAddressCityName(cityName, pageable);

        return getCatererDtoPagedResponse(caterers);
    }

    @Override
    @Cacheable(value = "caterersByName")
    public PagedResponse<CatererDto> getCaterersByName(String name, Integer page, Integer size) {
        log.debug("fetching caterers for name : {}", name);
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);

        Page<Caterer> caterers = catererRepository.findCaterersByName(name, pageable);
        return getCatererDtoPagedResponse(caterers);
    }

    private PagedResponse<CatererDto> getCatererDtoPagedResponse(Page<Caterer> caterersPage) {
        if (caterersPage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), caterersPage.getNumber(), caterersPage.getSize(), caterersPage.getTotalElements(),
                    caterersPage.getTotalPages(), caterersPage.isLast());
        }
        List<CatererDto> caterersList = caterersPage.stream()
                .map(caterer -> catererToCatererDto.convert(caterer))
                .map(caterer -> caterer.add(getSelfLink(caterer.getId())))
                .collect(Collectors.toList());

        return new PagedResponse<>(caterersList, caterersPage.getNumber(), caterersPage.getSize(), caterersPage.getTotalElements(), caterersPage.getTotalPages(),
                caterersPage.isLast());
    }

    @Override
    @Cacheable(value = "catererById", key = "#id")
    public CatererDto getCatererById(String id) {
        log.info("going to fetch data from db for id : {}", id);
        Optional<Caterer> catererOptional = catererRepository.findCatererById(id);
        CatererDto caterer = catererToCatererDto.convert(catererOptional.orElseThrow(() -> new EntityNotFoundException(Caterer.class, "id", id)));
        caterer.add(getSelfLink(caterer.getId()));
        return caterer;
    }

    @Override
    @CacheEvict(cacheNames = {"catererById", "caterersByName", "caterersByCity"}, allEntries = true)
    public CatererDto save(CatererDto catererDto) {
        Caterer detachedCaterer = catererDtoToCaterer.convert(catererDto);
        Caterer savedCaterer = catererRepository.save(detachedCaterer);
        log.debug("Saved Caterer:" + savedCaterer.getName());
        CatererDto returnCaterer = catererToCatererDto.convert(savedCaterer);
        returnCaterer.add(getSelfLink(savedCaterer.getId()));
        return returnCaterer;
    }

    private Link getSelfLink(String id){
        return linkTo(CatererController.class).slash("id").slash(id).withSelfRel();
    }
}
