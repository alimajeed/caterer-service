package com.hunzaconsulting.catererservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String phoneNumber;
    private String mobileNumber;
    private String email;
}
