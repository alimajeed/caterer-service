package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.controller.CatererController;
import com.hunzaconsulting.catererservice.converter.CatererDtoToCaterer;
import com.hunzaconsulting.catererservice.converter.CatererToCatererDto;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.exception.ResourceNotFoundException;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import com.hunzaconsulting.catererservice.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "caterers")
public class CatererService {

    private CatererRepository catererRepository;
    private CatererToCatererDto catererToCatererDto;
    private CatererDtoToCaterer catererDtoToCaterer;

    private ModelMapper modelMapper;

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

    public PagedResponse<CatererDto> getCityCaterers(String cityName, Integer page, Integer size){
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);

        Page<Caterer> caterersPage = catererRepository.findCaterersByAddressCityName(cityName, pageable);

        if (caterersPage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), caterersPage.getNumber(), caterersPage.getSize(), caterersPage.getTotalElements(),
                    caterersPage.getTotalPages(), caterersPage.isLast());
        }

        List<CatererDto> caterersList = caterersPage.stream()
                .map(caterer -> catererToCatererDto.convert(caterer))
                .map(caterer -> caterer.add(getSelfLink(caterer.getName())))
                .collect(Collectors.toList());

        return new PagedResponse<>(caterersList, caterersPage.getNumber(), caterersPage.getSize(), caterersPage.getTotalElements(), caterersPage.getTotalPages(),
                caterersPage.isLast());
    }

    @Cacheable
    public CatererDto getCaterer(String name) {
        System.out.println("fetch caterer");
        Optional<Caterer> catererOptional = catererRepository.findCatererByName(name);
        CatererDto caterer = catererToCatererDto.convert(catererOptional.orElseThrow(() -> new ResourceNotFoundException("caterer", "name", name)));
        caterer.add(getSelfLink(caterer.getName()));
        return caterer;
    }

    @CacheEvict(allEntries = true)
    public CatererDto save(CatererDto catererDto) {
        System.out.println("save caterer");
        Caterer detachedCaterer = catererDtoToCaterer.convert(catererDto);
        Caterer savedCaterer = catererRepository.save(detachedCaterer);
        log.debug("Saved Caterer:" + savedCaterer.getName());
        return catererToCatererDto.convert(savedCaterer).add(getSelfLink(savedCaterer.getName()));
    }

    private Link getSelfLink(String name){
        return linkTo(CatererController.class).slash(name).withSelfRel();
    }
}
