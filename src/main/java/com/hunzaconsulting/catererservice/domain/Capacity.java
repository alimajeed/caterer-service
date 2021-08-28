package com.hunzaconsulting.catererservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Capacity {

    private int minGuestNo;
    private int maxGuestNo;
}
