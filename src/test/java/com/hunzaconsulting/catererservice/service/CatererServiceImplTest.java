package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.domain.Address;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.AddressDto;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.mapper.CatererMapper;
import com.hunzaconsulting.catererservice.mapper.CatererMapperImpl;
import com.hunzaconsulting.catererservice.message.MessageProducer;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatererServiceImplTest {

    @Mock
    CatererRepository catererRepository;

    @Mock
    MessageProducer messageProducer;

    CatererMapper catererMapper;

    CatererServiceImpl catererService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        catererMapper = new CatererMapperImpl(new ModelMapper());
        catererService = new CatererServiceImpl(catererRepository, catererMapper, messageProducer);
    }

    @Test
    void testGetByCity() {
        String city = "city";
        Caterer caterer1 = Caterer.builder().id("id1")
                .address(Address.builder().cityName(city).build())
                .build();

        Caterer caterer2 = Caterer.builder().id("id2")
                .address(Address.builder().cityName(city).build())
                .build();

        Page<Caterer> catererPage = new PageImpl<Caterer>(List.of(caterer1, caterer2));

        when(catererRepository.findCaterersByAddressCityName(anyString(), any())).thenReturn(catererPage);

        //when
        int page = 1;
        PagedResponse<CatererDto> pageByCity = catererService.getPageByCity(city, page, 2);
        //then
        assertEquals(page-1, pageByCity.getPage());
        assertEquals(2, pageByCity.getSize());
        assertEquals(page, pageByCity.getTotalPages());
        assertEquals(city, pageByCity.getContent().get(0).getAddress().getCityName());
    }

    @Test
    void getCaterersByName() {
        String name = "name";
        int page = 1;
        int size = 2;
        Caterer caterer1 = Caterer.builder().id("id1").name(name).build();

        Caterer caterer2 = Caterer.builder().id("id2").name(name).build();

        Page<Caterer> catererPage = new PageImpl<Caterer>(List.of(caterer1, caterer2));

        when(catererRepository.findCaterersByName(anyString(), any())).thenReturn(catererPage);

        //when
        PagedResponse<CatererDto> pageByName = catererService.getPageByName(name, page, size);
        //then
        assertEquals(page-1, pageByName.getPage());
        assertEquals(size, pageByName.getSize());
        assertEquals(name, pageByName.getContent().get(page).getName());
    }

    @Test
    void getCatererById() {
        String id1 = "id1";
        String name = "name";

        Caterer caterer1 = Caterer.builder().id(id1).name(name).build();

        when(catererRepository.findCatererById(anyString())).thenReturn(Optional.of(caterer1));

        //when
        CatererDto catererById = catererService.getById(id1);

        //then
        assertEquals(name, catererById.getName());
    }

    @Test
    void save() {
        String name = "name";
        String id = "someId";
        String rel = "self";

        CatererDto caterer1 = new CatererDto();
        caterer1.setName(name);

        Caterer returnCaterer = new Caterer();
        returnCaterer.setId(id);
        returnCaterer.setName(caterer1.getName());

        when(catererRepository.save(any())).thenReturn(returnCaterer);

        //when
        CatererDto savedCaterer = catererService.save(caterer1);
        //then
        assertEquals(caterer1.getName(), savedCaterer.getName());
        assertTrue(savedCaterer.hasLink(rel));
        assertNotNull(savedCaterer.getId());
    }
}