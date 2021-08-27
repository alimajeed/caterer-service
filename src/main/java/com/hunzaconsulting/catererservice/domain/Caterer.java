package com.hunzaconsulting.catererservice.domain;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Caterer {

    private ModelMapper modelMapper;

    @Id
    private String id;
    private String name;
    private Address address;
    private Capacity capacity;
    private Contact contact;

}
