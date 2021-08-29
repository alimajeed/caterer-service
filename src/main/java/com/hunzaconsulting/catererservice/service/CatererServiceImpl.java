package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.controller.CatererController;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.exception.EntityNotFoundException;
import com.hunzaconsulting.catererservice.mapper.CatererMapper;
import com.hunzaconsulting.catererservice.message.MessageProducer;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import com.hunzaconsulting.catererservice.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

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
    private CatererMapper catererMapper;
    private MessageProducer messageProducer;

    @Override
    @Cacheable(value = "caterersByCity")
    public PagedResponse<CatererDto> getPageByCity(String cityName, Integer page, Integer size){
        log.debug("fetching caterers for city : {}", cityName);
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Caterer> caterers = catererRepository.findCaterersByAddressCityName(cityName, pageable);

        return getCatererDtoPagedResponse(caterers);
    }

    @Override
    @Cacheable(value = "caterersByName")
    public PagedResponse<CatererDto> getPageByName(String name, Integer page, Integer size) {
        log.debug("fetching caterers for name : {}", name);
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Caterer> caterers = catererRepository.findCaterersByName(name, pageable);
        return getCatererDtoPagedResponse(caterers);
    }

    private PagedResponse<CatererDto> getCatererDtoPagedResponse(Page<Caterer> caterersPage) {
        if (caterersPage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), caterersPage.getNumber(), caterersPage.getSize(), caterersPage.getTotalElements(),
                    caterersPage.getTotalPages(), caterersPage.isLast());
        }
        List<CatererDto> caterersList = caterersPage.stream()
                .map(caterer -> catererMapper.catererToCatererDto(caterer))
                .map(caterer -> caterer.add(getSelfLink(caterer.getId())))
                .collect(Collectors.toList());

        return new PagedResponse<>(caterersList, caterersPage.getNumber(), caterersPage.getSize(), caterersPage.getTotalElements(), caterersPage.getTotalPages(),
                caterersPage.isLast());
    }

    @Override
    @Cacheable(value = "catererById", key = "#id")
    public CatererDto getById(String id) {
        log.info("going to fetch data from db for id : {}", id);
        Optional<Caterer> catererOptional = catererRepository.findCatererById(id);
        CatererDto caterer = catererMapper.catererToCatererDto(catererOptional.orElseThrow(() -> new EntityNotFoundException(Caterer.class, "id", id)));
        caterer.add(getSelfLink(caterer.getId()));
        return caterer;
    }

    @Override
    @CacheEvict(cacheNames = {"catererById", "caterersByName", "caterersByCity"}, allEntries = true)
    public CatererDto save(CatererDto catererDto) {
        Caterer detachedCaterer = catererMapper.catererDtoToCaterer(catererDto);
        Caterer savedCaterer = catererRepository.save(detachedCaterer);
        log.debug("Saved Caterer : {}", savedCaterer.getName());
        messageProducer.sendMessage("caterers", savedCaterer.getId(), savedCaterer.toString());
        CatererDto returnCaterer = catererMapper.catererToCatererDto(savedCaterer);
        returnCaterer.add(getSelfLink(savedCaterer.getId()));
        return returnCaterer;
    }

    private Link getSelfLink(String id){
        return linkTo(CatererController.class).slash("id").slash(id).withSelfRel();
    }
}
