package com.hunzaconsulting.catererservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;

@Document
@Data
public class Caterer {

    @Id
    private String id;
    private String name;
    private Address address;
    private Capacity capacity;
    private Contact contact;
}
