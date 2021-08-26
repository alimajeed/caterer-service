package com.hunzaconsulting.catererservice.command;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CatererCommand extends RepresentationModel<CatererCommand> {

    private String id;
    private String name;
    private AddressCommand address;
    private CapacityCommand capacity;
    private ContactCommand contact;
}
