package com.hunzaconsulting.catererservice.command;

import lombok.Data;

@Data
public class CapacityCommand {

    private int minGuestNo;
    private int maxGuestNo;
}
