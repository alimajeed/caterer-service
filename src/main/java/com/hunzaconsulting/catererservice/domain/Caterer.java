package com.hunzaconsulting.catererservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Caterer {

    @Id
    private String id;
    private String name;
    private Address address;
    private Capacity capacity;
    private Contact contact;

}
