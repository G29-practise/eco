package org.example.eco.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateDto{
    private String city;
    private String country;
    private int postCode;
    private String region;
}
