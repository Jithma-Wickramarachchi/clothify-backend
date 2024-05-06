package edu.icet.clothifybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String getHouseNumber;
    private String street;
    private String city;
    private String state;
    private String postalCode;
}
