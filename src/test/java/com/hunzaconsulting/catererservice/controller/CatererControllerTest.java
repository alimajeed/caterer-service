package com.hunzaconsulting.catererservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunzaconsulting.catererservice.dto.AddressDto;
import com.hunzaconsulting.catererservice.dto.CapacityDto;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.dto.ContactDto;
import com.hunzaconsulting.catererservice.exception.EntityNotFoundException;
import com.hunzaconsulting.catererservice.handler.ApiExceptionHandler;
import com.hunzaconsulting.catererservice.payload.PagedResponse;
import com.hunzaconsulting.catererservice.service.CatererService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CatererControllerTest {

    @Mock
    CatererService catererService;

    @InjectMocks
    CatererController catererController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(catererController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void saveCaterer() throws Exception {
        AddressDto addressDto = AddressDto.builder()
                .cityName("someCity")
                .streetAddress("someStreet")
                .build();

        CapacityDto capacityDto = CapacityDto.builder()
                .minGuestNo(1)
                .maxGuestNo(10)
                .build();

        ContactDto contactDto = ContactDto.builder()
                .email("someEmail@host.com")
                .mobileNumber("1231231231")
                .build();

        CatererDto caterer = CatererDto.builder()
                .name("Caterer1")
                .address(addressDto)
                .capacity(capacityDto)
                .contact(contactDto)
                .build();

        CatererDto savedCaterer = new CatererDto();
        savedCaterer.setName(caterer.getName());
        savedCaterer.setCapacity(caterer.getCapacity());
        savedCaterer.setAddress(caterer.getAddress());
        savedCaterer.setContact(caterer.getContact());

        when(catererService.save(caterer)).thenReturn(savedCaterer);

        mockMvc.perform(post(CatererController.BASE_URL + "/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(caterer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Caterer1")));
    }

    @Test
    void fetchCaterersByName() throws Exception {


        CatererDto caterer1 = CatererDto.builder().name("SameName").build();

        CatererDto caterer2 = CatererDto.builder().name("SameName").build();

        PagedResponse pagedResponse = PagedResponse.builder()
                .content(List.of(caterer1, caterer2))
                .page(0)
                .size(2)
                .totalPages(1)
                .totalElements(2)
                .build();

        when(catererService.getPageByName(anyString(), anyInt(), anyInt()))
                .thenReturn(pagedResponse);

        mockMvc.perform(get(CatererController.BASE_URL + "/name/SameName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void fetchCaterersById() throws Exception {
        CatererDto caterer1 = CatererDto.builder().name("Caterer1").build();

        when(catererService.getById(anyString()))
                .thenReturn(caterer1);

        //when
        mockMvc.perform(get(CatererController.BASE_URL + "/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Caterer1")));
    }

    @Test
    void fetchCaterersByCity() throws Exception {
        AddressDto addressDto = AddressDto.builder().cityName("sameCity").build();

        CatererDto caterer1 = CatererDto.builder().name("Caterer1").address(addressDto).build();

        CatererDto caterer2 = CatererDto.builder().name("Caterer2").address(addressDto).build();

        PagedResponse pagedResponse = PagedResponse.builder()
                .content(List.of(caterer1, caterer2))
                .page(0)
                .size(2)
                .totalPages(1)
                .totalElements(2)
                .build();

        when(catererService.getPageByCity(anyString(), anyInt(), anyInt()))
                .thenReturn(pagedResponse);

        //when
        mockMvc.perform(get(CatererController.BASE_URL + "/city/sameCity")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(catererService.getById(anyString())).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get(CatererController.BASE_URL + "/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}