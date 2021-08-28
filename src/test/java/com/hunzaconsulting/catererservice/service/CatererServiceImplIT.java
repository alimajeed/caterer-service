package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.mapper.CatererMapper;
import com.hunzaconsulting.catererservice.mapper.CatererMapperImpl;
import com.hunzaconsulting.catererservice.message.MessageProducer;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import com.hunzaconsulting.catererservice.startup.CatererBootstrap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CatererServiceImplIT {

    @Autowired
    CatererRepository catererRepository;

    CatererMapper catererMapper;

    @Autowired
    MessageProducer messageProducer;

    CatererService catererService;

    @BeforeEach
    void setUp() {
        CatererBootstrap catererBootstrap = new CatererBootstrap(catererRepository);
        catererBootstrap.onApplicationEvent(null);

        catererMapper = new CatererMapperImpl(new ModelMapper());
        catererService = new CatererServiceImpl(catererRepository, catererMapper, messageProducer);
    }

    @Test
    public void testSave() {
        List<Caterer> all = catererRepository.findAll();
        Caterer caterer = all.get(0);
        CatererDto catererDto = new CatererMapperImpl(new ModelMapper()).catererToCatererDto(caterer);

        //when
        String nameChange = "nameChange";
        catererDto.setName(nameChange);
        CatererDto savedCaterer = catererService.save(catererDto);

        //then
        assertEquals(nameChange, savedCaterer.getName());
        assertEquals(caterer.getAddress().getCityName(), savedCaterer.getAddress().getCityName());
    }

    @Test
    public void testGetById() {
        List<Caterer> all = catererRepository.findAll();
        Caterer caterer = all.get(0);

        CatererDto returnCaterer = catererService.getById(caterer.getId());

        assertEquals(caterer.getId(), returnCaterer.getId());
    }

    @Test
    public void testGetPageByName(){
        int page = 0;
        int size = 2;
        List<Caterer> all = catererRepository.findAll();
        Caterer caterer = all.get(0);

        PagedResponse<CatererDto> pageByName = catererService.getPageByName(caterer.getName(), page, size);

        assertEquals(page, pageByName.getPage());
        assertEquals(size, pageByName.getSize());
        assertEquals(caterer.getName(), pageByName.getContent().get(page).getName());
    }

    @Test
    public void testGetByCity() {
        int page = 0;
        int size = 2;
        List<Caterer> all = catererRepository.findAll();
        Caterer caterer = all.get(0);

        PagedResponse<CatererDto> pageByCity = catererService.getPageByCity(caterer.getAddress().getCityName(), page, size);

        assertEquals(page, pageByCity.getPage());
        assertEquals(size, pageByCity.getSize());
        assertEquals(caterer.getName(), pageByCity.getContent().get(page).getName());
    }
}