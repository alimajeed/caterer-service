package com.hunzaconsulting.catererservice.command;

import lombok.Data;

@Data
public class AddressCommand {

    private String cityName;
    private String streetAddress;
    private String postalCode;

}
