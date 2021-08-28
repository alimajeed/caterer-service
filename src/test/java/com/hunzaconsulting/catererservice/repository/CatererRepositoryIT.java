package com.hunzaconsulting.catererservice.repository;

import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.startup.CatererBootstrap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
class CatererRepositoryIT {

    @Autowired
    CatererRepository catererRepository;

    @BeforeEach
    void setUp() {
        CatererBootstrap catererBootstrap = new CatererBootstrap(catererRepository);
        catererBootstrap.onApplicationEvent(null);
    }

    @Test
    void findCaterersByName() {
        Page<Caterer> caterers = catererRepository.findCaterersByName("name5", PageRequest.of(0, 2));
        assertNotNull(caterers);
        assertEquals(0, caterers.getNumber());
        assertEquals(2, caterers.getSize());
        assertEquals(2, caterers.getContent().size());
        assertEquals(4, caterers.getTotalElements());
    }

    @Test
    void findCaterersByAddressCityName() {
        Page<Caterer> caterers = catererRepository.findCaterersByAddressCityName("city1", PageRequest.of(1, 3));
        assertNotNull(caterers);
        assertEquals(1, caterers.getNumber());
        assertEquals(3, caterers.getSize());
        assertEquals(3, caterers.getContent().size());
        assertEquals(6, caterers.getTotalElements());
    }

    @Test
    void findCatererById() {
        Page<Caterer> caterers = catererRepository.findCaterersByName("name1", PageRequest.of(0, 1));
        String id = caterers.getContent().get(0).getId();
        Optional<Caterer> caterer1 = catererRepository.findCatererById(id);
        assertNotNull(caterer1.get());
        assertEquals(id, caterer1.get().getId());
    }
}